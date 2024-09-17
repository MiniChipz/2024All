$stykke = Read-Host "Hvad skal regnes?"
$ting = ""

if ($stykke.Contains("+")) { 
    for ($i = 0; $i -lt $stykke.Length; $i++) {
        $plus = $stykke[$i]

        if ($plus -ne "+") { 
            $ting += $plus
            [int]$tal1 = $stykke[0]
            [int]$tal2 = $stykke[1]
            $tal3 = $tal1 + $tal2
        }
    }
}

Write-Host $ting.Replace(" ","")
Write-Host $tal1 $tal2 $tal3