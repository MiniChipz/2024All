$pathm = Test-Path -Path "C:\Users\Malte\Desktop\SpamFolder"
$tal = 0
while ($true) {
    if ($pathm -eq $true) {
        $tal++
        New-Item -Path "C:\Users\Malte\Desktop\SpamFolder" -ItemType File -Name "Fil$tal"
        Write-Host "Fil$tal"
    } else {
        New-Item -ItemType Directory -Path "C:\Users\Malte\Desktop\SpamFolder"
        Write-Host "Folder made"
        $pathm = $true
    }
    
}