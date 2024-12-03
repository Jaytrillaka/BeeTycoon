import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class BeeStatsPanel extends JPanel {
    private JTable beeTable;
    private DefaultTableModel tableModel;
    private Player player;

    public BeeStatsPanel(Player player) {
        this.player = player;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        refresh();
    }

    public void refresh() {
        tableModel = initializeBeeStatsTable();
        beeTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Allow editing only the "Bee Name" column
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(Color.BLACK);
                c.setForeground(Color.YELLOW);
                return c;
            }
        };

        beeTable.setGridColor(Color.DARK_GRAY);
        beeTable.setFont(new Font("Arial", Font.PLAIN, 14));
        beeTable.setRowHeight(20);
        beeTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());

        removeAll();
        add(new JScrollPane(beeTable), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public JTable getTable() {
        return beeTable; // Provide access to the JTable
    }

    private DefaultTableModel initializeBeeStatsTable() {
        String[] columnNames = {"Bee Name", "Type", "Nectar Rate", "Journey Ready", "Cooldown"};
        List<Bee> bees = player.getOwnedBees();
        String[][] data = new String[bees.size()][5];

        for (int i = 0; i < bees.size(); i++) {
            Bee bee = bees.get(i);
            data[i] = new String[]{
                    bee.getName(),
                    bee.getType().toString(),
                    String.valueOf((int) bee.getNectarRate()),
                    bee.canGoOnJourney() ? "Yes" : "No",
                    bee.getCooldown() > 0 ? bee.getCooldown() + "s" : "Ready"
            };
        }

        return new DefaultTableModel(data, columnNames);
    }

    private static class HeaderRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel headerLabel = new JLabel(value.toString(), SwingConstants.CENTER);
            headerLabel.setOpaque(true);
            headerLabel.setBackground(Color.BLACK);
            headerLabel.setForeground(Color.YELLOW);
            headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
            return headerLabel;
        }
    }
}
