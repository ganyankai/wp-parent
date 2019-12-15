package cn.dante.p04decortor;

public class PacketHTMLHeaderCreator extends PacketDecorator{

    public PacketHTMLHeaderCreator(IPacketCreator packetCreator) {
        super(packetCreator);
    }

    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(component.handleContent());
        sb.append("</html>");
        sb.append("</body>\n");
        return sb.toString();
    }

}
