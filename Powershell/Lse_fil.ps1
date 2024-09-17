New-Item -ItemType File -Path "C:\Users\D100-DBD-JaBa\Desktop" -Name "Teste filleee.txt" #vi laver en fil med newitem 
Add-Content -Path "C:\Users\D100-DBD-JaBa\Desktop\Teste filleee.txt" "Hejsa denne er én ord" #vi adder tekst til filen
Start-Sleep -Seconds 5
Get-Content -Path "C:\Users\D100-DBD-JaBa\Desktop\Teste filleee.txt" #vi læser teksten fra filen