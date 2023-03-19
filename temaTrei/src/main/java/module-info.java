module com.example.tematrei {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.tematrei to javafx.fxml;
    exports com.example.tematrei;
    exports com.example.tematrei.controller;
    opens com.example.tematrei.controller to javafx.fxml;
  //  exports com.example.tematrei.database;
    exports com.example.tematrei.service;
    exports com.example.tematrei.repository;
    exports com.example.tematrei.domain;
   // opens com.example.tematrei.database to javafx.fxml;
    exports com.example.tematrei.repository.db;
}