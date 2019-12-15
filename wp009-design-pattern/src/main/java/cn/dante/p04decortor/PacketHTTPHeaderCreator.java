package cn.dante.p04decortor;

public class PacketHTTPHeaderCreator extends PacketDecorator{

    public PacketHTTPHeaderCreator(IPacketCreator packetCreator) {
        super(packetCreator);
    }
    
    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("Cache-Control:no-cache\n");
        sb.append("Date:Month \n");
        sb.append(component.handleContent());
        return sb.toString();
    }

}
