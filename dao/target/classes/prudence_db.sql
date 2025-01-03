-- Table des utilisateurs
CREATE TABLE User (
    ID_User INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(100) NOT NULL,
    Role ENUM('admin', 'accountant', 'director', 'secretaire'),
    archived BOOLEAN DEFAULT FALSE
);

-- Table des logs des utilisateurs
CREATE TABLE Logs (
    ID_Log INT PRIMARY KEY AUTO_INCREMENT,
    ID_User INT,
    Action VARCHAR(255),
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_User) REFERENCES User(ID_User)
);

-- Table des familles
CREATE TABLE Family (
    id_family INT PRIMARY KEY AUTO_INCREMENT,
    Nom_Responsable VARCHAR(50),
    Prenom_Responsable VARCHAR(50),
    Titre VARCHAR(50),
    Adresse_1 VARCHAR(100),
    Adresse_2 VARCHAR(100),
    Code_Ville VARCHAR(50),
    Telephone VARCHAR(20),
    CNIE VARCHAR(20),
    archived BOOLEAN DEFAULT FALSE
);

-- Table des élèves
CREATE TABLE Student (
    id_student INT PRIMARY KEY AUTO_INCREMENT,
    ID_Inscription INT,
    id_family INT,
    ID_ClassLevel INT,
    Matricule VARCHAR(50),
    First_Name VARCHAR(50),
    Last_Name VARCHAR(50),
    Gender VARCHAR(10),
    Date_Of_Birth DATE,
    Address VARCHAR(200),
    Phone_Number VARCHAR(20),
    Nationality VARCHAR(50),
    photo_path VARCHAR(255),
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Inscription) REFERENCES Inscription(ID_Inscription),
    FOREIGN KEY (id_family) REFERENCES Family(id_family)
    FOREIGN KEY (ID_ClassLevel) REFERENCES ClassLevel(ID_ClassLevel);
);

-- Table des enseignants
CREATE TABLE Teachers (
    ID_Teacher INT PRIMARY KEY AUTO_INCREMENT,
    First_Name VARCHAR(50),
    Last_Name VARCHAR(50),
    Email VARCHAR(50),
    Subject VARCHAR(50),
    Phone_Number VARCHAR(20),
    archived BOOLEAN DEFAULT FALSE
);

-- Table des documents
CREATE TABLE Documents (
    ID_Document INT PRIMARY KEY AUTO_INCREMENT,
    Document_Type VARCHAR(50),
    ID_Student INT,
    File_Path VARCHAR(200),
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Student) REFERENCES Student(id_student)
);

-- Table des paiements
CREATE TABLE Payments (
    ID_Payment INT PRIMARY KEY AUTO_INCREMENT,
    ID_Student INT,
    ID_Mode INT,
    Payment_Type VARCHAR(50),
    Payment_Date DATE,
    Amount DECIMAL(10, 2),
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Student) REFERENCES Student(id_student),
    FOREIGN KEY (ID_Mode) REFERENCES PaymentMode(ID_Mode)
);

-- Table des inscriptions
CREATE TABLE Inscription (
    ID_Inscription INT PRIMARY KEY AUTO_INCREMENT,
    ID_Student INT,
    DateDebutInscription DATE,
    DateFinInscription DATE,
    FraisInscription DECIMAL(10, 2),
    MontantInscription DECIMAL(10, 2),
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Student) REFERENCES Student(id_student)
);

-- Table des modes de paiement
CREATE TABLE PaymentMode (
    ID_Mode INT PRIMARY KEY AUTO_INCREMENT,
    Mode_Name VARCHAR(50),
    archived BOOLEAN DEFAULT FALSE
);

-- Table des types de paiement
CREATE TABLE TypePaiement (
    ID_TypePaiement INT PRIMARY KEY AUTO_INCREMENT,
    ID_Paiement INT,
    TypePaiement VARCHAR(50),
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Paiement) REFERENCES Payments(ID_Payment)
);

-- Table des paiements des élèves
CREATE TABLE PaiementStudent (
    ID_PaiementStudent INT PRIMARY KEY AUTO_INCREMENT,
    ID_Inscription INT,
    Type_PaiementStudent VARCHAR(50),
    Date_PaiementStudent DATE,
    Montant DECIMAL(10, 2),
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Inscription) REFERENCES Inscription(ID_Inscription)
);

-- Table des classes (niveaux)
CREATE TABLE ClassLevel (
    ID_ClassLevel INT PRIMARY KEY AUTO_INCREMENT,
    Level_Name VARCHAR(50) NOT NULL,
    Description VARCHAR(255),
    ID_Referent INT,
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Referent) REFERENCES Teachers(ID_Teacher)
);

-- Table des reçus de paiement
CREATE TABLE Receipts (
    ID_Receipt INT PRIMARY KEY AUTO_INCREMENT,
    ID_Payment INT,
    Receipt_Date DATE,
    Amount DECIMAL(10, 2),
    FOREIGN KEY (ID_Payment) REFERENCES Payments(ID_Payment)
);

-- Table des paiements par tranche
CREATE TABLE PaymentSchedules (
    ID_Schedule INT PRIMARY KEY AUTO_INCREMENT,
    ID_Student INT,
    Due_Date DATE,
    Amount DECIMAL(10, 2),
    Status ENUM('paid', 'pending', 'overdue'),
    archived BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Student) REFERENCES Student(id_student)
);

-- Table des reçus de paiement par famille
CREATE TABLE ReceiptsF (
    ID_Receipt INT PRIMARY KEY AUTO_INCREMENT,
    ID_Family INT,
    Receipt_Date DATE,
    Total_Amount DECIMAL(10, 2),
    FOREIGN KEY (ID_Family) REFERENCES Family(id_family)
);




