$Ting = 1
while($Ting -ne 5){
    $Tid = 5 - $Ting
    Write-Host "Skole er slut om $Tid sekunder"
    $Ting += 1
    Start-Sleep -Seconds 3600
}