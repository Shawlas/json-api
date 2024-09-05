package codes.ghostface.type;

import org.jetbrains.annotations.NotNull;

public enum ServerPacketType {
    FTP_DATA("FTP Data"),
    POP3_DATA("POP3 Data"),
    IMAP_DATA("IMAP Data"),
    WEBSOCKET_MESSAGE("WebSocket Message"),
    DNS_RESPONSE("DNS Response"),
    SSH_DATA_TRANSFER("SSH Data Transfer"),
    TCP_SEGMENT("TCP Segment"),
    TCP_ACK("TCP ACK"),
    TCP_FIN("TCP FIN"),
    UDP_DATAGRAM("UDP Datagram"),
    IP_DATAGRAM("IP Datagram"),
    ICMP_ECHO_REPLY("ICMP Echo Reply"),
    ICMP_DESTINATION_UNREACHABLE("ICMP Destination Unreachable"),
    ICMP_TIME_EXCEEDED("ICMP Time Exceeded"),
    ETHERNET_FRAME("Ethernet Frame"),
    ARP_REQUEST("ARP Request"),
    ARP_REPLY("ARP Reply"),
    DHCP_OFFER("DHCP Offer"),
    DHCP_ACK("DHCP ACK"),
    NTP_RESPONSE("NTP Response"),
    HTTP_RESPONSE("HTTP Response"),
    HTTP_DATA("HTTP Data");

    private final String description;

    ServerPacketType(@NotNull String description) {
        this.description = description;
    }

    @NotNull
    public String getDescription() {
        return description;
    }
}
