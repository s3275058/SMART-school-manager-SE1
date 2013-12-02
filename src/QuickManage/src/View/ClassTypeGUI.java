/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Main.Main;
import Model.ClassType;
import Model.ImportExportSCV;
import Model.Model;
import Model.Ultility;
import View.CustomComponent.CButton;
import View.CustomComponent.CLabel;
import View.CustomComponent.CPanel;
import View.Input.ClassTypeInput;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author anh
 */
public class ClassTypeGUI extends CPanel implements Ultility {

    private String username;
    private CPanel topPanel, menuPanel, mainPn, containerPanel, contentPanel, botPanel, smallPanel;
    private JMenu fileMenu, viewMenu, helpMenu, toolMenu;
    private JMenuItem exitAction, switchViewAction, aboutAction, userGuideAction, logoutAction;
    private JMenuItem addAction, editAction, deleteAction, deleteAllAction, activeAction;
    private CButton accountBt, teacherBt, studentBt, classBt, invoiceBt, classTypeBt;
    private CButton addBt, editBt, deleteBt, activeBt, deleteAllBt, logoutBt, importBt, exportBt;
    private ArrayList<ClassType> selectedItems = new ArrayList<ClassType>();
    private static int index = 0;
    Model model = Model.getModel();
ResourceBundle resourse = model.getResources();
    ImportExportSCV imp = new ImportExportSCV();

    public ClassTypeGUI(String username) {

        super(new BorderLayout(), BACKGROUND_PIC);
        this.username = username;
        topPanel = new CPanel(TOP_PANEL_PIC);
        mainPn = new CPanel(new BorderLayout());
        menuPanel = new CPanel(new BorderLayout());


        menuPanel.add(getMenuBar(), BorderLayout.NORTH);
        menuPanel.add(getToolBar(), BorderLayout.CENTER);

        checkButton();

        contentPanel = getContentPanel();
        topPanel.add(menuPanel);
        mainPn.add(getLeftPanel(), BorderLayout.WEST);
        mainPn.add(contentPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(mainPn, BorderLayout.CENTER);

    }

    private CPanel getLeftPanel() {
        CPanel leftPanel = new CPanel(new GridLayout(8, 1), LEFT_PANEL_PIC);
        accountBt = new CButton(resourse.getString("ACCOUNT_TXT"));
        teacherBt = new CButton(resourse.getString("TEACHER_TXT"));
        studentBt = new CButton(resourse.getString("STUDENT_TXT"));
        classBt = new CButton(resourse.getString("CLASS_TXT"));
        invoiceBt = new CButton(resourse.getString("INVOICES_TXT"));
        classTypeBt = new CButton(resourse.getString("CLASS_TYPE_TXT"));



        if (model.isManager(username)) {
            accountBt.setVisible(false);
            classTypeBt.setVisible(false);
        }

        accountBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new AccountsGUI(username));
            }
        });

        teacherBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new TeacherGUI(username));
            }
        });

        studentBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new StudentGUI(username));
            }
        });
        classBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new ClassGUI(username));
            }
        });
        invoiceBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new InvoiceGUI(username));
            }
        });
        classTypeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new ClassTypeGUI(username));
            }
        });




        CPanel thisPanel = new CPanel(new BorderLayout(), ITEM_MENU_PIC);
        thisPanel.add(classTypeBt);

        leftPanel.add(accountBt);
        leftPanel.add(teacherBt);
        leftPanel.add(studentBt);
        leftPanel.add(thisPanel);
        leftPanel.add(classBt);
        leftPanel.add(invoiceBt);

        leftPanel.setBorder(new EmptyBorder(0, 5, 0, 10));

        return leftPanel;
    }

    private JToolBar getToolBar() {
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);

        addBt = new CButton(ADD_BUTTON);
        editBt = new CButton(EDIT_BUTTON);
        deleteBt = new CButton(DELETE_BUTTON);
        deleteAllBt = new CButton(DELETE_ALL_BUTTON);
        activeBt = new CButton(ACTIVE_BUTTON);
        logoutBt = new CButton(LOG_OUT_BUTTON);
        importBt = new CButton(IMPORT_BUTTON);
        exportBt = new CButton(EXPORT_BUTTON);


        activeBt.setEnabled(false);

        toolBar.add(addBt);
        toolBar.add(editBt);
        toolBar.add(deleteBt);
        toolBar.add(deleteAllBt);
        toolBar.add(activeBt);
        toolBar.add(importBt);
        toolBar.add(exportBt);
        JButton langBt = new JButton(model.getResources().getString("LANGUAGE_TXT"));
        toolBar.add(langBt);
        langBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.switchLanguage();
                Main.changePanel(new ClassTypeGUI(username));
            }
        });
        toolBar.add(logoutBt);

        addBt.setToolTipText(resourse.getString("ADD_TXT"));
        editBt.setToolTipText(resourse.getString("EDIT_TXT"));
        deleteBt.setToolTipText(resourse.getString("DELETE_TXT"));
        deleteAllBt.setToolTipText(resourse.getString("DELETE_ALL_TXT"));
        activeBt.setToolTipText(resourse.getString("ACTIVE_TXT"));
        logoutBt.setToolTipText(resourse.getString("LOGOUT_TXT"));

        // action listener
        exportBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                imp.activeExport(CLASS_TYPE_PORT);
            }
        });

        importBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imp.activeImport(CLASS_TYPE_PORT);
            }
        });

        editBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassTypeInput(selectedItems.get(0), getThis());
                updateContainerPanel();
                checkButton();
            }
        });


        deleteBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < selectedItems.size(); i++) {
                    model.deleteClassType(selectedItems.get(i));
                }
                updateContainerPanel();
            }
        });

        addBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ClassTypeInput(getThis());
                index = getTotalIndex();
                updateContainerPanel();
            }
        });


        editAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassTypeInput(selectedItems.get(0), getThis());
                updateContainerPanel();
                checkButton();
            }
        });

        logoutBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new LoginGUI());
            }
        });

        toolBar.setOpaque(false);
        toolBar.setBorder(new EmptyBorder(5, 0, 0, 0));
        return toolBar;
    }

    //menu bar and Menu
    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        fileMenu = new JMenu(resourse.getString("FILE_MENU"));
        viewMenu = new JMenu(resourse.getString("VIEW_MENU"));
        helpMenu = new JMenu(resourse.getString("HELP_MENU"));
        toolMenu = new JMenu(resourse.getString("TOOL_MENU"));

        //JMenuItem
        exitAction = new JMenuItem(resourse.getString("EXIT_TXT"));
        switchViewAction = new JMenuItem(resourse.getString("SWITCH_TXT"));
        aboutAction = new JMenuItem(resourse.getString("ABOUT_TXT"));
        userGuideAction = new JMenuItem(resourse.getString("USER_GUIDE_TXT"));
        addAction = new JMenuItem(resourse.getString("ADD_TXT"));
        editAction = new JMenuItem(resourse.getString("EDIT_TXT"));
        deleteAction = new JMenuItem(resourse.getString("DELETE_TXT"));
        deleteAllAction = new JMenuItem(resourse.getString("DELETE_ALL_TXT"));
        activeAction = new JMenuItem(resourse.getString("ACTIVE_TXT"));
        activeAction.setEnabled(false);
        logoutAction = new JMenuItem(resourse.getString("LOGOUT_TXT"));

        //action listener
        addAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                new ClassInput(getThis());
                index = getTotalIndex();
                updateContainerPanel();
            }
        });

        deleteAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < selectedItems.size(); i++) {
                    model.deleteClassType(selectedItems.get(i));
                }
                updateContainerPanel();
            }
        });



        exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (getDialogConfirmation(QUIT_CONFRIM_TXT) == JOptionPane.YES_OPTION) {
                    try {
                        model.saveAllData();
                        System.exit(0);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TeacherGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        logoutAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new LoginGUI());
            }
        });

        //add shorcut
        addAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
        editAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        deleteAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteAllAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
        logoutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        switchViewAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        userGuideAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        //Combine Menu
        toolMenu.add(addAction);
        toolMenu.add(editAction);
        toolMenu.add(activeAction);
        toolMenu.add(deleteAction);
        toolMenu.add(deleteAllAction);
        fileMenu.add(logoutAction);
        fileMenu.add(exitAction);
        viewMenu.add(switchViewAction);
        helpMenu.add(aboutAction);
        helpMenu.add(userGuideAction);

        menuBar.add(fileMenu);
        menuBar.add(toolMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        menuBar.setOpaque(false);


        return menuBar;
    }

    private CPanel getContentPanel() {
//        contentPanel = new CPanel(new GridLayout(1, 1));
        contentPanel = new CPanel(new GridLayout(1, 1));
        smallPanel = new CPanel(new BorderLayout());

        containerPanel = getContainerPanel();
        botPanel = getBotPanel();
        smallPanel.add(containerPanel, BorderLayout.CENTER);
        smallPanel.add(botPanel, BorderLayout.SOUTH);

        contentPanel.add(smallPanel);
        contentPanel.updateUI();


        return contentPanel;

    }

    private CPanel getContainerPanel() {
        int a = 0;
        containerPanel = new CPanel(new GridLayout(10, 1));

        if (index < getTotalIndex()) {
            a = 9;
        } else {
            a = model.getClassTypeList().size() % 9;
        }
        for (int i = 0; i < a; i++) {
            containerPanel.add(getDetailPanel(model.getClassTypeList().get(((index) * 9) + i)));

        }

        containerPanel.updateUI();

        return containerPanel;
    }

    private CPanel getDetailPanel(final ClassType a) {
        final CPanel itemPanel = new CPanel(new GridLayout(2, 3), ITEM_PANEL_PIC);


        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (itemPanel.isIsSelected() == false) {
                    itemPanel.setImage(SELECTED_ITEM_PANEL_PIC);
                    itemPanel.updateUI();
                    selectedItems.add(a);
                    itemPanel.setIsSelected(true);
                    checkButton();
                } else {
                    itemPanel.setImage(ITEM_PANEL_PIC);
                    itemPanel.updateUI();
                    selectedItems.remove(a);
                    itemPanel.setIsSelected(false);
                    checkButton();
                }
            }
        });

        itemPanel.add(new CLabel("ID: " + a.getId(), TEXT_SIZE));
        itemPanel.add(new CLabel(resourse.getString("NAME_TXT")+" " + a.getName(), TEXT_SIZE));
        itemPanel.add(new CLabel(resourse.getString("SUBJECT_TXT")+" " + CLASS_TYPE[a.getType()], TEXT_SIZE));
        itemPanel.add(new CLabel(resourse.getString("LESSON_WEEK_TXT")+":  " + a.getLessonWeek(), TEXT_SIZE));
        itemPanel.add(new CLabel(resourse.getString("FEE_45_TXT")+": " + "$" + a.getFeeLesson45(), TEXT_SIZE));
        itemPanel.add(new CLabel(resourse.getString("FEE_60_TXT")+": " + "$" + a.getFeeLesson60(), TEXT_SIZE));
        itemPanel.add(new CLabel(resourse.getString("INFO_TXT")+": " + "$" + a.getInfo(), TEXT_SIZE));


        itemPanel.updateUI();

        return itemPanel;
    }

    private CPanel getBotPanel() {
        botPanel = new CPanel(new BorderLayout());

        CPanel p = new CPanel();

        model.getClassTypeList().size();
        for (int i = 0; i <= getTotalIndex(); i++) {
            p.add(new CButton(i, index, this));
        }
        botPanel.add(p, BorderLayout.CENTER);
        return botPanel;
    }

    private int getTotalIndex() {
        int a = 0, b = 0, c = 0, d = 0;
        a = model.getClassTypeList().size();
        b = 9;
        if (a > 9) {
            c = a % b;
            d = (a - c) / b;
        }
        return d;
    }

    private void checkButton() {

        if (model.getClassTypeList().isEmpty()) {
            editBt.setEnabled(false);
            deleteBt.setEnabled(false);
            deleteAllBt.setEnabled(false);
            editAction.setEnabled(false);
            deleteAction.setEnabled(false);
            deleteAllAction.setEnabled(false);
        } else if (selectedItems.size() == 1) {
            editBt.setEnabled(true);
            deleteBt.setEnabled(true);
            deleteAllBt.setEnabled(true);
            editAction.setEnabled(true);
            deleteAction.setEnabled(true);
            deleteAllAction.setEnabled(true);
        } else if (selectedItems.size() > 1) {
            editBt.setEnabled(false);
            deleteBt.setEnabled(true);
            deleteAllBt.setEnabled(true);
            editAction.setEnabled(false);
            deleteAction.setEnabled(true);
            deleteAllAction.setEnabled(true);

        } else if (selectedItems.isEmpty()) {
            editBt.setEnabled(false);
            deleteBt.setEnabled(false);
            deleteAllBt.setEnabled(true);
            editAction.setEnabled(false);
            deleteAction.setEnabled(false);
            deleteAllAction.setEnabled(true);

        }


    }

    public static void setIndex(int index) {
        ClassTypeGUI.index = index;
    }

    public void updateContainerPanel() {
        selectedItems.clear();
        smallPanel.removeAll();
        smallPanel.updateUI();
        containerPanel = getContainerPanel();
        botPanel = getBotPanel();
        smallPanel.add(containerPanel, BorderLayout.CENTER);
        smallPanel.add(botPanel, BorderLayout.SOUTH);

    }

    private int getDialogConfirmation(String message) {
        int choose = JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.YES_NO_OPTION);
        return choose;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    private ClassTypeGUI getThis() {
        return this;
    }
}
