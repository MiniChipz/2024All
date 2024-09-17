$sekunder = 0
$minutter = 1
$timer = 0
$dage = 0

while (1 -eq 1) { 
    $sekunder++
    if ($sekunder -eq 60) { 
        $sekunder = 0
        $minutter++
    }

    if ($minutter -eq 60) { 
        $minutter = 0
        $timer++

    }


     if ($timer -eq 24) { 
        $timer = 0
        $dage++

    }




    if ($dage -ge 1) {
    Write-Host "Tiden er $dage dage, $timer timer, $minutter minutter og $sekunder sekunder"
    Start-Sleep -Seconds 1
    }
    elseif ($timer -ge 1) { 
    Write-Host "Tiden er $timer timer, $minutter minutter og $sekunder sekunder"
    Start-Sleep -Seconds 1
    }
    elseif ($sekunder -ge 0) {
    Write-Host "Tiden er $minutter minutter og $sekunder sekunder"
    Start-Sleep -Seconds 1
    }
    else { 
    Write-Host "Fejl"
    }

}