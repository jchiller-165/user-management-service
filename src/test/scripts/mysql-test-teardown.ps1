param(
    [string]$ProjectName
)

if (-not $ProjectName) {
    Write-Error "ERROR: You must provide ProjectName."
    exit 1
}

$ContainerName = "test-db-$ProjectName"

# Stop on any error
$ErrorActionPreference = "Stop"

Write-Host "Stopping and removing container '$ContainerName' ..."

# Try stopping; ignore if already stopped
try {
    docker stop $ContainerName | Out-Null
} catch {
    # Ignore errors
}

# Try removing; ignore if already removed
try {
    docker rm $ContainerName | Out-Null
} catch {
    # Ignore errors
}

Write-Host "Teardown complete."
