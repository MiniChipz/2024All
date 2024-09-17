# Linje nummer 2 importere AD modulet så vi kan kode ind i vores AD
Import-Module ActiveDirectory
# Her sætter vi variabler til vores path og vores domæne. Variabler er en form for pladsholder for teskt eller tal.
$CSVFilePath = "C:\Users\Administrator\Desktop\MyCSV.txt"
$MyMailDomain = "@corp.mit-it.dk"
# Her importere vi værdierne fra vores csv fil.
$Users = Import-Csv $CSVFilePath
# For hver bruger i brugere aka. vores csv fil gør den noget.
foreach ($User in $Users) {
	# Try og catch bruger man sammen og det fungere sådan: Når/hvis der kommer fejl i sin kode tager try den og kaster den over til catch som så opbevare fejlen i en variable.
	try {
		# Tilføjer én for hver bruger i csv filen
        $total += 1
		# Laver en ny bruger og giver brugeren fornavn og efternavn
    	New-ADUser -Name ($User.Givenname + " " + $User.Surname) `
		# Hernede sætter vi alle værdierne fra vores csv fil så de laver en bruger.
        	-SamAccountName $User.Samaccount `
        	-UserPrincipalName ($User.Samaccount + $MyMailDomain) `
        	-Surname $User.Surname `
        	-GivenName $User.Givenname `
        	-Path $User.OU `
            -AccountPassword (ConvertTo-SecureString $User.Password -AsPlainText -Force) `
        	-ChangePasswordAtLogon $false `
        	-Enabled $true
		# Her tilføjer vi brugeren til grupperne "Domain Users" og "Remote Desktop Users"
    	Add-ADGroupMember -Identity "Domain Users" -Members $User.Samaccount
    	Add-ADGroupMember -Identity "Remote Desktop Users" -Members $User.Samaccount
		# Her laver vi en ny variable til vores homefolder path
    	$HomeFolderPath = $User.HomeFolder
		# Her laver vi et nyt directory til vores homeholder
    	New-Item -ItemType Directory -Path $HomeFolderPath
		# Her laver vi et homedrive og profilepath
    	Set-ADUser $User.Samaccount -HomeDrive "H:" -HomeDirectory $HomeFolderPath -ProfilePath $User.ProfilePath
    } catch {

    }
}
# Her skriver den hvor mange brugere den har lavet og resetter værdien bagefter.
Write-Host Du har lavet $total brugere.
$total = 0