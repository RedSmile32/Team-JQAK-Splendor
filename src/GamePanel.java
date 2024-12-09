import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener {
    // Constants
    private static final int MAX_TOKENS = 10;
    private static final int MAX_RESERVED_CARDS = 3;
    private static final int WINNING_SCORE = 15;
    private static final List<Card> deck = setupDeck();
    private static final List<Player> players = setupPlayers();
    private static final Map<Gem, Integer> gemPile = setupGemPile(players.size());

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Enums
    enum Gem { DIAMOND, SAPPHIRE, EMERALD, RUBY, ONYX, GOLD }

    // Classes for Game Elements
    static class Card {
        int level;
        int prestigePoints;
        Map<Gem, Integer> cost;
        Gem bonus;

        public Card(int level, int prestigePoints, Gem bonus, Map<Gem, Integer> cost) {
            this.level = level;
            this.prestigePoints = prestigePoints;
            this.bonus = bonus;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "level=" + level +
                    ", prestigePoints=" + prestigePoints +
                    ", bonus=" + bonus +
                    ", cost=" + cost +
                    '}';
        }
    }

    //Player class
    static class Player {
        String name;
        Map<Gem, Integer> tokens = new HashMap<>();
        Map<Gem, Integer> bonuses = new HashMap<>();
        List<Card> reservedCards = new ArrayList<Card>();
        List<Card> purchasedCards = new ArrayList<Card>();
        int prestigePoints = 0;

        public Player(String name) {
            this.name = name;
            for (Gem gem : Gem.values()) {
                tokens.put(gem, 0);
                bonuses.put(gem, 0);
            }
        }

        public void addCard(Card card) {
            purchasedCards.add(card);
            bonuses.put(card.bonus, bonuses.get(card.bonus) + 1);
            prestigePoints += card.prestigePoints;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", tokens=" + tokens +
                    ", bonuses=" + bonuses +
                    ", prestigePoints=" + prestigePoints +
                    '}';
        }
    }

    // Main Game Class
    public GamePanel() {
        // Setup game
        addMouseListener(this);
//        List<Card> deck = setupDeck();
//        List<Player> players = setupPlayers();
//        Map<Gem, Integer> gemPile = setupGemPile(players.size());
//        // Game loop
//        Scanner scanner = new Scanner(System.in);
//        boolean gameOver = false;
//        while (!gameOver) {
//            for (Player player : players) {
////                System.out.println(player.name + "'s turn!");
////                System.out.println("Your current status: " + player);
////                System.out.println("Available gems: " + gemPile);
////                System.out.println("Available cards: " + deck);
////                System.out.println("Choose an action:");
////                System.out.println("1. Take 3 different gems");
////                System.out.println("2. Take 2 same gems");
////                System.out.println("3. Reserve a card");
////                System.out.println("4. Purchase a card");
//
////                int choice = scanner.nextInt();
////                switch (choice) {
////                    case 1 -> takeGems(player, gemPile, scanner);
////                    case 2 -> takeSameGems(player, gemPile, scanner);
////                    case 3 -> reserveCard(player, deck, scanner);
////                    case 4 -> purchaseCard(player, deck, gemPile, scanner);
////                    default -> System.out.println("Invalid choice!");
////                }
////
////                if (player.prestigePoints >= WINNING_SCORE) {
////                    gameOver = true;
////                    System.out.println(player.name + " wins the game with " + player.prestigePoints + " points!");
////                    break;
////                }
//            }
//        }

//        scanner.close();
    }

    // Methods for setup and actions
    private static List<Card> setupDeck() {
        List<Card> deck = new ArrayList<>();
        Map<Gem, Integer> cost1 = Map.of(Gem.DIAMOND, 1, Gem.SAPPHIRE, 2);
        deck.add(new Card(1, 1, Gem.EMERALD, cost1));
        return deck; // Add more cards for full game
    }

    private static List<Player> setupPlayers() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter number of players:");
//        int numPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
//            System.out.println("Enter name of player " + i + ":");
            players.add(new Player(String.valueOf(i)));
        }
        return players;
    }

    private static Map<Gem, Integer> setupGemPile(int numPlayers) {
        Map<Gem, Integer> gemPile = new HashMap<>();
        int gemsPerType = switch (numPlayers) {
            case 2 -> 4;
            case 3 -> 5;
            default -> 7;
        };
        for (Gem gem : Gem.values()) {
            gemPile.put(gem, gem == Gem.GOLD ? 5 : gemsPerType);
        }
        return gemPile;
    }

    private static void takeGems(Player player, Map<Gem, Integer> gemPile, Scanner scanner) {
        System.out.println("Choose 3 different gems:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter gem type:");
            Gem gem = Gem.valueOf(scanner.next().toUpperCase());
            if (gemPile.get(gem) > 0) {
                gemPile.put(gem, gemPile.get(gem) - 1);
                player.tokens.put(gem, player.tokens.get(gem) + 1);
            } else {
                System.out.println("Not enough gems!");
            }
        }
    }

    private static void takeSameGems(Player player, Map<Gem, Integer> gemPile, Scanner scanner) {
        System.out.println("Enter gem type:");
        Gem gem = Gem.valueOf(scanner.next().toUpperCase());
        if (gemPile.get(gem) >= 4) {
            gemPile.put(gem, gemPile.get(gem) - 2);
            player.tokens.put(gem, player.tokens.get(gem) + 2);
        } else {
            System.out.println("Not enough gems!");
        }
    }

    private static void reserveCard(Player player, List<Card> deck, Scanner scanner) {
        System.out.println("Enter card index to reserve:");
        int index = scanner.nextInt();
        if (index >= 0 && index < deck.size() && player.reservedCards.size() < MAX_RESERVED_CARDS) {
            player.reservedCards.add(deck.remove(index));
            System.out.println("Card reserved!");
        } else {
            System.out.println("Invalid choice or too many reserved cards!");
        }
    }

    private static void purchaseCard(Player player, List<Card> deck, Map<Gem, Integer> gemPile, Scanner scanner) {
        System.out.println("Enter card index to purchase:");
        int index = scanner.nextInt();
        if (index >= 0 && index < deck.size()) {
            Card card = deck.get(index);
            if (canPurchase(player, card)) {
                payForCard(player, card, gemPile);
                player.addCard(deck.remove(index));
                System.out.println("Card purchased!");
            } else {
                System.out.println("Not enough resources!");
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static boolean canPurchase(Player player, Card card) {
        for (Map.Entry<Gem, Integer> cost : card.cost.entrySet()) {
            int totalAvailable = player.tokens.get(cost.getKey()) + player.bonuses.get(cost.getKey());
            if (totalAvailable < cost.getValue()) return false;
        }
        return true;
    }

    private static void payForCard(Player player, Card card, Map<Gem, Integer> gemPile) {
        for (Map.Entry<Gem, Integer> cost : card.cost.entrySet()) {
            int required = cost.getValue();
            int availableTokens = player.tokens.get(cost.getKey());
            if (availableTokens >= required) {
                player.tokens.put(cost.getKey(), availableTokens - required);
                gemPile.put(cost.getKey(), gemPile.get(cost.getKey()) + required);
            } else {
                gemPile.put(cost.getKey(), gemPile.get(cost.getKey()) + availableTokens);
                player.tokens.put(cost.getKey(), 0);
            }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(ImageHandler.gameBackground(), 0, 0, getWidth(), getHeight(), null);

        for (int i = 0; i < players.size(); i++) {
            int extraWidth = getWidth()/2 + 5;
            int extraHeight = 15;
            if (i == 1 || i == 3) {
                extraWidth = getWidth() * 3/4 + 5;
            }
            if (i >= 2) {
                extraHeight = getHeight() * 3/8 + 15;
            }
            g.drawString("player " + players.get(i).name + ": " + players.get(i).prestigePoints + " points", extraWidth,extraHeight);
        }
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight() * 3/4);
        g.drawLine(0, getHeight() * 3/4, getWidth(), getHeight() * 3/4);
        g.drawLine(getWidth() * 3/4, 0, getWidth() * 3/4, getHeight() * 3/4);
        g.drawLine(getWidth() / 2, getHeight() * 3/8, getWidth(), getHeight() * 3/8);
        g.drawLine(0, getHeight() * 3/16, getWidth() / 2, getHeight() * 3/16);

        int gemSize = 30;
        int x = 20, y = getHeight() * 3 / 4 + 20; // Starting position for gems
        for (Map.Entry<Gem, Integer> entry : gemPile.entrySet()) {
            Gem gem = entry.getKey();
            int count = entry.getValue();
            g.setColor(getGemColor(gem));
            g.fillOval(x, y, gemSize, gemSize);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(count), x + gemSize / 2 - 5, y + gemSize + 15);
            x += gemSize + 10; // Move to the next position
        }

    }

    private Color getGemColor(Gem gem) {
        return switch (gem) {
            case DIAMOND -> Color.WHITE;
            case SAPPHIRE -> Color.BLUE;
            case EMERALD -> Color.GREEN;
            case RUBY -> Color.RED;
            case ONYX -> Color.BLACK;
            case GOLD -> Color.YELLOW;
        };
    }
}
