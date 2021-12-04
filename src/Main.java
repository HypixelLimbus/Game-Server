import me.sub.gameServer.GameServer;

public class Main {
    public static GameServer server;

    public static void main(String[] args){
        int port = args.length > 0
                ? Integer.parseInt(args[0])
          : 8080;
        new GameServer().run(port);
        server = new GameServer();
        server.run(port);
    }

}
