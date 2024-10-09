$file_path = "C:\Users\Malte\Documents\Programmering\git_commits.txt"

# Check if the file exists
if (Test-Path $file_path) {
    # Get the content and convert it to an integer
    $file = Get-Content $file_path
    [int]$commit_number = [int]$file

    # Increment the commit number
    $commit_number++

    # Save the incremented commit number back to the file
    Set-Content -Path $file_path -Value $commit_number
} else {
    # If the file doesn't exist, create it and set the initial commit number to 1
    New-Item -ItemType File -Path $file_path -Force
    $commit_number = 1
    Set-Content -Path $file_path -Value $commit_number
}

# Execute git commands
git add "C:\Users\Malte\Documents\Programmering"
git commit -m "Commit number $commit_number"
git push
