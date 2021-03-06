package gossip.stat.client;

import gossip.stat.client.olsrd.IRoutingTable;
import gossip.stat.client.olsrd.OLSRDRoutingTable;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

public class CyclonStatic {

    /**
     * @param args
     * @throws IOException 
     */
    public static void runCyclon(final int basePort, final int maxClients, final boolean isSeed,  
    		final InetAddress seedIP, final InetAddress statServerAddress, final int statServerPort, 
    		final InetAddress networkInterfaceIP) throws IOException {

        Runnable peerFactory = new Runnable() {
        	
        	
            private int portOffset = 0;
            
            @Override
            public void run() {
                while (true && portOffset < maxClients) {
                    try {
                        Random r = new Random();
                        CyclonPeer p = new CyclonPeer(networkInterfaceIP, basePort + (portOffset++), statServerAddress, statServerPort);
                        if (portOffset > 1) {
                            p.addSeedNode(networkInterfaceIP, basePort + r.nextInt(portOffset - 1));
                        }
                        new Thread(p).start();
                        Thread.sleep(r.nextInt(1500));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        new Thread(peerFactory).start();
    }
}
