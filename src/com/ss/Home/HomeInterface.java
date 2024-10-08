package com.ss.Home;

import com.ss.Database.DbQuery;
import com.ss.Login.*;
import com.ss.RemoteConnection.RemoteCmds;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomeInterface extends JFrame {
    private static String email;
    private static String cusID;
    private static int isPro = 0;

    private static String plan;

    private static JPanel wrapperPanel;
    private static JScrollPane scrollPane;
    private static JButton upgradeButton;
    private static JLabel userName;
    private static JPanel usrnamePanel;
    private static JPanel planPanel;
    private static JLabel planLabel = new JLabel(plan);

    public HomeInterface(String email, String cusID, int Pro){
        this.email = email;
        this.cusID = cusID;
        this.isPro = Pro;
        this.wrapperPanel = new JPanel(new GridLayout(0, 1));
        this.scrollPane = new JScrollPane();
        this.upgradeButton = new JButton("Upgrade to Pro");
        this.planPanel = new JPanel(new BorderLayout());
//        this.planPanel.setBackground(new Color(255, 255, 255, 0));
        this.usrnamePanel = new JPanel(new BorderLayout());
        this.userName = new JLabel(email);
        initComponents();
    }

    public static String getCusID(){
        return cusID;
    }
    private void initComponents(){
        Container container = getContentPane();

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { // Creating gradiant colour background
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                int width = getWidth();
                int height = getHeight();

                Color startColor = new Color(109, 210, 225);
                Color endColor = new Color(37, 122, 211);

                GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);

                g2d.dispose();
            }
        };
        gradientPanel.setLayout(new BorderLayout());

        JPanel sideLeft = new JPanel(new BorderLayout());
        sideLeft.setBackground(new Color(255, 255, 255, 0));

        JPanel sideRight = new JPanel(new BorderLayout());
        sideRight.setBackground(new Color(255, 255, 255, 0));

        // Creating elements for the left side--------------------------------------------------------------------------
//        ImageIcon image = new ImageIcon("src/Images/logo.png");
        java.net.URL imageURL = getClass().getResource("/Images/logo.png");
        ImageIcon image = new ImageIcon(imageURL);
        image.setImage(image.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20));

//        JPanel usrnamePanel = new JPanel(new BorderLayout());
        usrnamePanel.setBackground(new Color(255, 255, 255, 0));
        usrnamePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 290, 20));

//        JLabel userName = new JLabel(email);
        userName.setFont(new Font("Arial", Font.PLAIN, 18));
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        if (isPro == 1){
            plan = "Pro";
            planLabel.setText(plan);
            planLabel.setFont(new Font("Arial", Font.PLAIN, 22));
            planLabel.setForeground(Color.RED);
            planLabel.setHorizontalAlignment(SwingConstants.CENTER);
            planLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, -45, 0));
            planPanel.add(planLabel, BorderLayout.NORTH);
            planPanel.add(planLabel, BorderLayout.CENTER);
            planPanel.setBackground(new Color(255, 255, 255, 0));
            System.out.println("Plan panel Refreshed");
        }
        else{
            plan = "Free Plan";
//            JLabel planLabel = new JLabel(plan);
            planLabel.setText(plan);
            planLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            planLabel.setForeground(Color.BLACK);

            upgradeButton.setFont(new Font("Arial", Font.PLAIN, 18));
            upgradeButton.setBackground(new Color(255, 255, 255));

            upgradeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            planPanel.setBackground(new Color(255, 255, 255, 0));
            planPanel.add(planLabel, BorderLayout.NORTH);
            planPanel.add(planLabel, BorderLayout.CENTER);
            planPanel.add(upgradeButton, BorderLayout.SOUTH);
            upgradeButton.addActionListener(e -> {
               new UpgradePopUp(cusID);
            });


        }

        planLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        planLabel.setHorizontalAlignment(SwingConstants.CENTER);
        planLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, -45, 0));

        JButton LogoutButton = new JButton("Logout");
        LogoutButton.setFont(new Font("Arial", Font.PLAIN, 18));
        LogoutButton.setBackground(new Color(255, 255, 255));
        LogoutButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Creating elements for the right side--------------------------------------------------------------------------
        JLabel appName = new JLabel("Sync Sentry");
        appName.setFont(new Font("Arial", Font.PLAIN, 37));
        appName.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        appName.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(0, 0, 0, 200));

        JLabel labelHead = new JLabel("Your Files");
        labelHead.setForeground(Color.WHITE);
        labelHead.setFont(new Font("Arial", Font.PLAIN, 20));
        labelHead.setHorizontalAlignment(SwingConstants.CENTER);
        labelHead.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

//        JPanel wrapperPanel = new JPanel(new GridLayout(0, 1));  // This is created at the top
        wrapperPanel.setBackground(new Color(255, 255, 255));
//        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(255, 255, 255));

        JPanel uploadPanel = new JPanel(new FlowLayout());
        uploadPanel.setBackground(new Color(255, 255, 255, 0));
        uploadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel uploadLabel = new JLabel("Upload a file");
        uploadLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        uploadLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        uploadLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton uploadButton = new JButton("Select");
        uploadButton.setFont(new Font("Arial", Font.PLAIN, 18));
        uploadButton.setBackground(new Color(255, 255, 255));
        uploadButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));


        // Adding the elements to the panels
        sideLeft.add(imageLabel, BorderLayout.NORTH);
        usrnamePanel.add(userName, BorderLayout.NORTH);
        usrnamePanel.add(planPanel);
        sideLeft.add(usrnamePanel, BorderLayout.CENTER);
        sideLeft.add(LogoutButton, BorderLayout.SOUTH);

        sideRight.add(appName, BorderLayout.NORTH);
        contentPanel.add(labelHead, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        uploadPanel.add(uploadLabel);
        uploadPanel.add(uploadButton);
        sideRight.add(contentPanel, BorderLayout.CENTER);
        sideRight.add(uploadPanel, BorderLayout.SOUTH);

        gradientPanel.add(sideLeft, BorderLayout.WEST);
        gradientPanel.add(sideRight, BorderLayout.CENTER);
        container.add(gradientPanel);

        //Adding enevt listeners to the buttons
        LogoutButton.addActionListener(e -> {
            dispose();
            new LoginInterface();
        });

        uploadButton.addActionListener(e -> {
            FileHandler.upload(cusID, isPro);
        });


        setTitle("Home");
        setSize(1080, 720);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        readFilesDataInDbCaller(); // Call the function to read the files data from the database
        scrollPane.setViewportView(wrapperPanel); // Set the wrapperPanel to the scrollPane as the view
    }



    public static void addDataToWrapper(String fName, String fSize, String fID, String fType){ // this function will add the files to the wrapperPanel and called in DbQuery.java readFilesDataInDb() function
        JPanel filePanel = new JPanel(new GridLayout(0, 5));
        filePanel.setName(fID); // Set the name of the panel to the file ID for easy identification
        filePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel fileName = new JLabel(fName+fType);
        fileName.setFont(new Font("Arial", Font.PLAIN, 18));
        fileName.setHorizontalAlignment(SwingConstants.CENTER);
        filePanel.add(fileName);

        JLabel fileSize = new JLabel("Size: "+fSize+" MB");
        fileSize.setFont(new Font("Arial", Font.PLAIN, 16));
        fileSize.setHorizontalAlignment(SwingConstants.CENTER);
        filePanel.add(fileSize);

        JButton downloadButton = new JButton("Download");
        downloadButton.setForeground(new Color(8, 173, 35));
        downloadButton.setFont(new Font("Arial", Font.PLAIN, 18));
        downloadButton.setBackground(new Color(255, 255, 255));
        downloadButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        filePanel.add(downloadButton);

        JButton renameButton = new JButton("Rename");
        renameButton.setForeground(Color.blue);
        renameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        renameButton.setBackground(new Color(255, 255, 255));
        renameButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        filePanel.add(renameButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.RED);
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.setBackground(new Color(255, 255, 255));
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        filePanel.add(deleteButton);

        wrapperPanel.add(filePanel);

        downloadButton.addActionListener(e -> {
            RemoteCmds.downloadFile(fName, fType);
        });

        renameButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog("Enter the new name for the file");
            if (newName != null){
                RemoteCmds.renameFile(fName, newName, fID, fType);
            }
        });

        deleteButton.addActionListener(e -> {
            RemoteCmds.deleteFile(fName,fID,fType);
        });

    }

    public void readFilesDataInDbCaller(){ // This function will call the readFilesDataInDb() function in DbQuery.java
        new DbQuery().readFilesDataInDb(cusID);
    }

    public static void reFreshWrapper(){ // used to refresh the home interface for every delete and rename
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
                wrapperPanel.removeAll();
                wrapperPanel.revalidate();
                wrapperPanel.repaint();
                new DbQuery().readFilesDataInDb(cusID); //recreate the items in the wrapper panel
                System.out.println("Wrapper panel Refreshed");
//            }
//        });
    }

    public static void reFreshPlanPanel(){ // used to refresh plan panel if upgrading to pro
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
                isPro = 1;
                planLabel.setText("Pro");
                planLabel.setFont(new Font("Arial", Font.PLAIN, 22));
                planLabel.setForeground(Color.RED);
                planPanel.remove(upgradeButton);
                planPanel.revalidate();
                planPanel.repaint();
                planPanel.setBackground(new Color(255, 255, 255, 0));
                System.out.println("Plan panel Refreshed");
//            }
//        });
    }

    public static void showMsgsucess(String msg){
        JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new HomeInterface("1@emil.com", "C001", 1);
    }
}
