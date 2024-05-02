# Spam Phishers

A template application that uses Playwright to spam sites that are trying to phish for information with fake user data.

Mockdata is located under `src/main/resources/mockdata.json`.

New mockdata can be created using [mockaroo](https://www.mockaroo.com/).

## Useful commands

Codegen:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen"
```

Codegen with device:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args='codegen --device="iPhone 13" playwright.dev'
```
