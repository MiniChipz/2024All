$StartTime = Get-Date

for ($i = 0; $i -lt 1000000; $i++) {
}

$EndTime = Get-Date

$elapsedTime = $EndTime - $StartTime

$totalMilliseconds = $elapsedTime.TotalMilliseconds
$milliseconds = [math]::Floor($totalMilliseconds)
$microseconds = ($totalMilliseconds - $milliseconds) * 1000

$totalTime = "{0:000}.{1:000000}" -f $milliseconds, $microseconds
Write-Host " "
Write-Host "Milliseconds: $totalTime"
Write-Host " "