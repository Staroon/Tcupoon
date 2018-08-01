package com.staroon.tcupoon.controller;

import com.staroon.tcupoon.model.Urls;
import com.staroon.tcupoon.tools.SqliteTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/17
 * Time: 15:33
 */
public class RecordsController implements Initializable {
    @FXML
    private TableView<Urls> urlsTable;

    @FXML
    private TableColumn<Urls, String> idCol;

    @FXML
    private TableColumn<Urls, String> urlCol;

    @FXML
    private TableColumn<Urls, String> localCol;

    @FXML
    private TableColumn<Urls, String> updateCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        urlCol.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
        localCol.setCellValueFactory(cellData -> cellData.getValue().origPathProperty());
        updateCol.setCellValueFactory(cellData -> cellData.getValue().uploadTimeProperty());

        // 设置 urlCol和localCol 两列可被编辑复制
        urlsTable.setEditable(true);
        urlCol.setCellFactory(TextFieldTableCell.forTableColumn());
        localCol.setCellFactory(TextFieldTableCell.forTableColumn());

        List<Urls> urlsList = SqliteTool.getUrlsList();
        ObservableList<Urls> list = FXCollections.observableArrayList();
        list.addAll(urlsList);
        urlsTable.setItems(list);
    }
}
