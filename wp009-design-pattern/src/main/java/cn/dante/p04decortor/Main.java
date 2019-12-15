package cn.dante.p04decortor;

import cn.dante.p03xiangyuan.IReportManager;
import cn.dante.p03xiangyuan.ReportManagerFactory;

public class Main {
    public static void main(String[] args) {
        IPacketCreator pc =
//                new PacketHTMLHeaderCreator(
                        new PacketHTTPHeaderCreator(
                                new PacketHTMLHeaderCreator(
                                        new PacketBodyCreator()
                                )
//                        )
                );
        System.out.println(pc.handleContent());
    }
}
