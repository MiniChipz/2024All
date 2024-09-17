[int]$sekunder = Read-Host "Hvor mange sekunder"
[int]$minutter = Read-Host "Hvor mange minutter"



while ($minutter -or $sekunder -ge 1) { 
    $sekunder--
    Write-Host "Der er $minutter minutter $sekunder sekunder tilbage"
    Start-Sleep -Seconds 1
    if ($sekunder -eq 1) { 
        if ($minutter -gt 0) { 
            $sekunder = 61
            $minutter--
        }

        }
    }

[System.Console]::beep(1000, 500)   
[System.Console]::beep(500, 500) 
[System.Console]::beep(1000, 500) 
[System.Console]::beep(500, 500) 
[System.Console]::beep(1000, 500) 
[System.Console]::beep(500, 500) 