public class HelloGoodbye {
    public static void main(String[] args) {
        String name = "";
        String lastName = "";

        if (args.length > 0)
            name = args[0];

        if (args.length > 1)
            lastName = args[1];

        System.out.println(String.format("Hello %s and %s.", name, lastName));
        System.out.println(String.format("Goodbye %s and %s.", lastName, name));
    }
}