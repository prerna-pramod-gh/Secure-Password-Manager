# Password Manager

# Secure Password Manager (Java + AES-256)  
**MCA Cybersecurity Project – Amity University Online**  
**Prerna Pramod** | December 2025

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Cybersecurity](https://img.shields.io/badge/Cybersecurity-Project-blueviolet?style=for-the-badge)
![Encryption](https://img.shields.io/badge/AES--256-Encryption-green?style=for-the-badge)

## Project Overview
A console-based password manager that securely stores usernames and passwords using **AES-256 encryption**.  
All data is encrypted on disk and protected by a single master password.

**Educational purpose only** – demonstrates real-world encryption concepts taught in cybersecurity courses.

## Features
- Master password protection (hashed with salt)
- AES-256 encryption for every stored credential
- Add / View / List passwords
- Zero external dependencies – pure Java
- Runs instantly on any machine with Java installed

## How to Run (Tested & Working – Dec 2025)
```bash
javac PasswordManager.java
java PasswordManager

What I Learned

Implementing symmetric encryption (AES) in Java
Secure password hashing and salting
File handling for persistent encrypted storage
Clean console UI for user interaction

**## Future Improvements** **(you can add these later)**
```bash
GUI using JavaFX/Swing
Password strength meter
Clipboard auto-copy (with timer clear)

Made for learning secure coding practices
Never use this for real passwords – for demo & academic use only
