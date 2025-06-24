param(
    [string]$ContainerRepo,
    [string]$ContainerVersion,
    [string]$ProjectName,
    [string]$MysqlEntryPoint
)

$ErrorActionPreference = "Stop"

if (-not $ContainerRepo -or -not $ContainerVersion -or -not $ProjectName) {
    Write-Error "ERROR: You must provide ContainerRepo, ContainerVersion, and ProjectName."
    exit 1
}

$ContainerName = "test-db-$ProjectName"
$MySQLRootPassword = "password"
$MySQLDatabase = "user_management"
$MySQLPort = 3307

Write-Host "Pulling MySQL image ${ContainerRepo}:${ContainerVersion} ..."
docker pull --platform linux/amd64 "${ContainerRepo}:${ContainerVersion}"

$existingContainer = docker ps -aq -f name=$ContainerName
if ($existingContainer) {
    Write-Host "Removing old container $ContainerName ..."
    docker rm -f $ContainerName | Out-Null
}

Write-Host "Starting MySQL Docker container ..."
docker run --platform linux/amd64 -d --name $ContainerName -e "MYSQL_ROOT_PASSWORD=$MySQLRootPassword" -e "MYSQL_DATABASE=$MySQLDatabase" -p "$MySQLPort`:3306" -v "$MysqlEntryPoint:/docker-entrypoint-initdb.d:ro,Z" "${ContainerRepo}:${ContainerVersion}" | Out-Null

Write-Host "Waiting for Docker to register the container ..."
do {
    $exists = docker ps -aq -f name=$ContainerName
    if (-not $exists) {
        Write-Host -NoNewline "."
        Start-Sleep -Seconds 1
    }
} while (-not $exists)
Write-Host ""

Write-Host "Waiting for Docker container to be running ..."
do {
    $state = docker inspect -f "{{.State.Running}}" $ContainerName 2>$null
    if ($state -ne "true") {
        Write-Host -NoNewline "."
        Start-Sleep -Seconds 1
    }
} while ($state -ne "true")
Write-Host ""

# ✅ Host TCP port check only
Write-Host "Waiting for TCP port $MySQLPort to be reachable from host ..."
$portOpen = $false
$maxAttempts = 30
$attempt = 0

do {
    $attempt++
    try {
        $tcp = New-Object System.Net.Sockets.TcpClient
        $tcp.Connect("localhost", $MySQLPort)
        $tcp.Close()
        $portOpen = $true
    } catch {
        $portOpen = $false
        Write-Host -NoNewline "."
        Start-Sleep -Seconds 2
    }
} while (-not $portOpen -and $attempt -lt $maxAttempts)
Write-Host ""

if (-not $portOpen) {
    throw "ERROR: TCP port $MySQLPort was not reachable in time."
}

Write-Host "MySQL is healthy and reachable on localhost:$MySQLPort!"
