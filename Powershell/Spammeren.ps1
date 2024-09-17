$tal = 1
while ($tal -le 100000) {
    md C:\Users\D-110-STD-01\Desktop -Name "Spam mappe"
    New-Item -ItemType File -Path "C:\Users\D-110-STD-01\Desktop\Spam mappe"
    $tal++
}