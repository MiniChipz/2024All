[Int] $Alder = Read-Host -Prompt "Skriv din alder her!"

if ($Alder -ge 18) { 
    Write-Host "Smut med dig! Ad"
    Write-Host "Kom tilbage når du er yngre!"
}

else { 
    Write-Host "Mmm kom her!"
    [Int] $Svar = Read-Host "1-10 vi boller"
    Write-Host "NO WAY!! Valgte du også $Svar !??!??"
    Start-Sleep -Seconds 2
    Start-Process "https://jerkmate.com/"
}
