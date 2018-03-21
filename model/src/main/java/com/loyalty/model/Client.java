package com.loyalty.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private String clientID;
    private String clientName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!clientID.equals(client.clientID)) return false;
        return !(clientName != null ? !clientName.equals(client.clientName) : client.clientName != null);

    }

    @Override
    public int hashCode() {
        int result = clientID.hashCode();
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID='" + clientID + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
