$svar = Read-Host "Vil du finde en eksisterende eller lave en ny forsendelse? (ny/eks)"
if ($svar -match "eks") {
    Get-ChildItem -Path "C:\Users\Malte\Desktop\Testeren\" -Recurse | Sort-Object Name -Descending | Format-Table Name, LastWriteTime -AutoSize
}
elseif ($svar -match "ny") {
    $fil = Read-Host "Hvad hedder filen?"
    New-Item -Path "C:\Users\Malte\Desktop\Testeren\" -ItemType File -Name "$fil.txt"
}