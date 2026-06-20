package view;

import model.Admin;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardView extends JFrame {

    private Admin admin;

    public AdminDashboardView(Admin admin) {

        this.admin = admin;

        setTitle("Admin Dashboard");

        setSize(600,500);

        setLocationRelativeTo(null);

        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                7,
                                1,
                                10,
                                10));

        JLabel welcome =
                new JLabel(
                        "Welcome Admin: "
                                + admin.getName(),

                        SwingConstants.CENTER);

        JButton playerButton =
                new JButton(
                        "Manage Players");

        JButton adminButton =
                new JButton(
                        "Manage Admins");

        JButton rankingButton =
                new JButton(
                        "Manage Rankings");

        JButton wordPoolButton =
                new JButton(
                        "Manage Word Pool");

        JButton categoryButton =
                new JButton(
                        "Manage Categories");

        JButton logoutButton =
                new JButton(
                        "Logout");

        panel.add(welcome);

        panel.add(playerButton);

        panel.add(adminButton);

        panel.add(rankingButton);

        panel.add(wordPoolButton);

        panel.add(categoryButton);

        panel.add(logoutButton);

        add(panel);

        playerButton.addActionListener(
                e -> new PlayerManagementView());

        adminButton.addActionListener(
                e -> new AdminManagementView());

        rankingButton.addActionListener(
                e -> new RankingManagementView());

        wordPoolButton.addActionListener(
                e -> new WordPoolManagementView());

        categoryButton.addActionListener(
                e -> new WordCategoryManagementView());

        logoutButton.addActionListener(
                e -> {

                    dispose();

                    new HomeView();
                });
    }
}