# Definér stier til den overvågede mappe og målmappen for hver filtype
$sourceFolder = "C:\Users\Malte\Desktop\Testeren"
$destinationFolders = @{

    ".txt" = "C:\Users\Malte\Desktop\Testeren\txt"
    ".png" = "C:\Users\Malte\Desktop\Testeren\png"
    ".pdf" = "C:\Users\Malte\Desktop\Testeren\pdf"
    ".jpg" = "C:\Users\Malte\Desktop\Testeren\png"
    # Tilføj flere filtyper og målmapper efter behov
}

# Opret en FileSystemWatcher til at overvåge den overvågede mappe
$watcher = New-Object System.IO.FileSystemWatcher
$watcher.Path = $sourceFolder
$watcher.IncludeSubdirectories = $true
$watcher.EnableRaisingEvents = $true

# Definér en handling, der skal udføres, når en ny fil oprettes
$action = {
    $file = $Event.SourceEventArgs.Name
    $fileType = [System.IO.Path]::GetExtension($file).ToLower()

    # Kontroller, om filtypen er defineret i destinationFolders
    if ($destinationFolders.ContainsKey($fileType)) {
        $destinationFolder = $destinationFolders[$fileType]
        $destinationPath = Join-Path -Path $destinationFolder -ChildPath $file

        # Flyt filen til målmappen
        Move-Item $Event.SourceEventArgs.FullPath $destinationPath -Force
        Write-Host "Filen '$file' er blevet flyttet til $destinationFolder."
    } else {
        Write-Host "Filtypen '$fileType' er ikke defineret i scriptet."
    }
}

# Tilføj handlingen til FileSystemWatcher-objektets Created-event
Register-ObjectEvent $watcher "Created" -Action $action

# Hold scriptet kørende, indtil det manuelt stoppes
try {
    while ($true) {
        Start-Sleep -Seconds 5
    }
} finally {
    # Ryd op ved afslutning
    Unregister-Event -SourceIdentifier FileCreated
    $watcher.Dispose()
}
