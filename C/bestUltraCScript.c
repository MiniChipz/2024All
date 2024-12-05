#include <stdio.h>

int main() {
    char string[32] = "Jeg kan ikke lide Mikkel";
    int nummer = 50;
    printf("%llu. et tal: %i", sizeof(string), nummer);
    if (5 == 5) {
        printf(", og 5 er 5");
    }
    return 0;
}