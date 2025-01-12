# Spam Phishing Sites

A template application used to spam sites that are trying to phish for information with fake user data.

Uses [Playwright](https://playwright.dev/) to automate the process of filling out forms on phishing sites.\
Uses [Faker](https://github.com/DiUS/java-faker) to generate fake user data.

## Useful commands

Codegen:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen"
```

Codegen with device:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args='codegen --device="iPhone 13" playwright.dev'
```
