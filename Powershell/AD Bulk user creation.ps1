Import-Module ActiveDirectory

$Users = Import-csv "C:\Users\Malte\Desktop\AD Users.txt"

foreach ($User in $Users) {

	$MyMailDomain = "@corp.mit-it.dk"

	# if (Get-ADUser -F {SamAccount -eq $User.SamAccount})
	# {
	# 	 Write-Warning "Brugeren "$User.Givenname" findes allerede."
	# }
	# else
	# {
		
    New-ADUser `
        -Name ($User.Givenname + " " + $User.Surname) `
        -SamAccountName $User.Samaccount `
        -UserPrincipalName ($User.Samaccount + $MyMailDomain) `
        -GivenName $User.Givenname `
        -Surname $User.Surname `
        -Enabled $True `
        -DisplayName $User.Samaccount `
        -Path $User.OU `
        -AccountPassword $User.Password `
        -ChangePasswordAtLogon $False
        
    Add-ADGroupMember -Identity "Domain Users" -Members $User.Samaccount
    Add-ADGroupMember -Identity "Remote Desktop Users" -Members $User.Samaccount

    $HomefolderPath = $User.Homefolder
    New-Item -ItemType directory -Path $HomefolderPath

    Set-ADUser $User.Samaccount -Homedrive "H:" -HomeDirectory $HomefolderPath -Profilepath $User.Profilepath
	}
# }