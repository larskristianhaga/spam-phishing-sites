# Spam Phishing Sites

A template application used to spam sites that are trying to phish for information with fake user data.

Uses [Playwright](https://playwright.dev/) to automate the process of filling out forms on phishing sites.\
Uses [Datafaker](https://github.com/datafaker-net/datafaker) to generate fake user data.

## Useful commands

Codegen:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen"
```

Codegen with device:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args='codegen --device="iPhone 13" playwright.dev'
```


TODO:
- [ ] Better install for both windows, and mac.
- [ ] Faker data to insert on the first run.
- [ ] A way to stagger the threads so they don't all run at the same time, some tests will then get a 401/403 error.
- [X] Helper method for generating Coupon codes.
- [X] Helper method for randomeness, Eg, takes in a chance and gives an int back, or something.
- 