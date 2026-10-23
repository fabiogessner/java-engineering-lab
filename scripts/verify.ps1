$ErrorActionPreference = 'Stop'
$repo = Split-Path $PSScriptRoot -Parent
$localJdk = Join-Path $repo '.tools\jdk'
$localMaven = Join-Path $repo '.tools\maven'
if (Test-Path $localJdk) {
    $env:JAVA_HOME = (Get-ChildItem $localJdk -Directory | Select-Object -First 1).FullName
    $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
}
$mavenCommand = 'mvn.cmd'
if (Test-Path $localMaven) {
    $mavenHome = (Get-ChildItem $localMaven -Directory | Select-Object -First 1).FullName
    $mavenCommand = Join-Path $mavenHome 'bin\mvn.cmd'
}
Push-Location $repo
try {
    & $mavenCommand --batch-mode --no-transfer-progress clean verify
    if ($LASTEXITCODE -ne 0) { throw "Maven failed with exit code $LASTEXITCODE" }
} finally {
    Pop-Location
}
