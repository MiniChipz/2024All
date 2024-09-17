$linje = 8890956
while (1 -eq 1) { 
    Add-Content -Path "C:\Users\D-110-STD-01\Desktop\New folder\Spam_Fil2.txt" "Linje nummer $linje"
    $linje++
    Write-Host "Linje nummer $linje"
}