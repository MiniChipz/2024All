# (Get-Process | Where-Object {$_.FileName -eq "C:\Users\Malte\Desktop\Testeren\txt\testningen.txt"}).Kill()

$string = ""

$ting = $string -match "/W"

Write-Host $ting

[int] $tal1 = Read-Host "Hvad skal det første tal være?"
[int] $tal2 = Read-Host "Hvad skal det andet tal være?"

$resultat = [math]::Pow($tal1, $tal2)

Write-Host $resultat

if (1 -lt 1) {
    Write-Host "1"
}

elseif (1 -lt 0) {
    <# Action when this condition is true #>
    Write-Host "2"
}

elseif (1 -lt 2) {
    <# Action when this condition is true #>
    Write-Host "3"
}
Write-Host "4"

switch ($twioadj) {
    "hehe" {Write-Host}
    Default {}
}