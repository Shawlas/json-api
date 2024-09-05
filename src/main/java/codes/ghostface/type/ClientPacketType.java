package codes.ghostface.type;

import org.jetbrains.annotations.NotNull;

public enum ClientPacketType {
    HTTP_REQUEST("HTTP Request"),
    HTTP_DATA("HTTP Data"),
    FTP_COMMAND("FTP Command"),
    FTP_DATA("FTP Data"),
    SMTP_COMMAND("SMTP Command"),
    SMTP_DATA("SMTP Data"),
    POP3_COMMAND("POP3 Command"),
    IMAP_COMMAND("IMAP Command"),
    WEBSOCKET_HANDSHAKE("WebSocket Handshake"),
    WEBSOCKET_MESSAGE("WebSocket Message"),
    DNS_QUERY("DNS Query"),
    SSH_CONNECTION_REQUEST("SSH Connection Request"),
    SSH_COMMAND_EXECUTION("SSH Command Execution"),
    SSH_DATA_TRANSFER("SSH Data Transfer"),
    TCP_SEGMENT("TCP Segment"),
    TCP_SYN("TCP SYN"),
    TCP_ACK("TCP ACK"),
    TCP_FIN("TCP FIN"),
    UDP_DATAGRAM("UDP Datagram"),
    IP_DATAGRAM("IP Datagram"),
    ICMP_ECHO_REQUEST("ICMP Echo Request"),
    ETHERNET_FRAME("Ethernet Frame"),
    ARP_REQUEST("ARP Request"),
    ARP_REPLY("ARP Reply"),
    DHCP_DISCOVER("DHCP Discover"),
    DHCP_REQUEST("DHCP Request"),
    NTP_REQUEST("NTP Request");

    private final @NotNull String description;

    ClientPacketType(@NotNull String description) {
        this.description = description;
    }

    public @NotNull String getDescription() {
        return description;
    }
}

