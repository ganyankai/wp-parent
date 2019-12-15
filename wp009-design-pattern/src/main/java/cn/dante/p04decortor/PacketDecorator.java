package cn.dante.p04decortor;

public abstract class PacketDecorator implements IPacketCreator{
    IPacketCreator component;

    public PacketDecorator(IPacketCreator packetCreator) {
        component = packetCreator;
    }

}
