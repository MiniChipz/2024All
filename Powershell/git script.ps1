$file_path = "C:\Users\Malte\Documents\Programmering\git_commits.txt"

if (Test-Path $file_path) {
    $file = Get-Content $file_path
    [int]$commit_number = [int]$file

    $commit_number++

    Set-Content -Path $file_path -Value $commit_number
} else {
    New-Item -ItemType File -Path $file_path -Force
    $commit_number = 1
    Set-Content -Path $file_path -Value $commit_number
}

git add "C:\Users\Malte\Documents\Programmering"
git commit -m "Commit number $commit_number"
git push
