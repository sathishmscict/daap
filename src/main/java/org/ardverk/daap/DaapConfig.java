/*
 * Digital Audio Access Protocol (DAAP) Library
 * Copyright (C) 2004-2010 Roger Kapsi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ardverk.daap;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Interface for DaapServer Settings
 * 
 * @author Roger Kapsi
 */
public class DaapConfig {

    public static final Object NO_PASSWORD = "NO_PASSWORD";
    public static final Object PASSWORD = "PASSWORD";
    public static final Object USERNAME_AND_PASSWORD = "USERNAME_AND_PASSWORD";

    public static final Object BASIC_SCHEME = "BASIC_SCHEME";
    public static final Object DIGEST_SCHEME = "DIGEST_SCHEME";

    /**
     * The "HTTP" style name of the Server. Original is "iTunes/4.2 (Mac OS X)"
     */
    public static final String DEFAULT_SERVER_NAME = "DaapServer/0.2 ("
            + System.getProperty("os.name") + ")";

    /**
     * Default iTunes Port is 3689
     */
    public static final int DEFAULT_PORT = 3689;

    /**
     * See ServerSocket/SocketChannel description
     */
    public static final int DEFAULT_BACKLOG = 0;

    /**
     * The maximum number of connections (you must multipy this value with 2 as
     * streams are separate connections)
     */
    public static final int DEFAULT_MAX_CONNECTIONS = 5;

    /** Default buffer size. */
    public static final int DEFAULT_BUFFER_SIZE = 2048;

    protected String name;
    protected InetSocketAddress address;
    protected int backlog;
    protected int maxConnections;
    protected int bufferSize;

    protected Object authenticationMethod;
    protected Object authenticationScheme;

    public DaapConfig() {
        name = DEFAULT_SERVER_NAME;
        address = new InetSocketAddress(DEFAULT_PORT);
        backlog = DEFAULT_BACKLOG;
        maxConnections = DEFAULT_MAX_CONNECTIONS;
        bufferSize = DEFAULT_BUFFER_SIZE;

        authenticationMethod = NO_PASSWORD;
        authenticationScheme = BASIC_SCHEME;
    }

    public void setServerName(String name) {
        this.name = name;
    }

    /**
     * HTTP style name for the Server
     * 
     * @return the HTTP style name of the Server (e.g. DaapServer/0.1)
     */
    public String getServerName() {
        return name;
    }

    /**
     * Returns the authentication method. Default is {@link #NO_PASSWORD}
     */
    public Object getAuthenticationMethod() {
        return authenticationMethod;
    }

    /**
     * Sets the authentication method. Valid arguments are:
     * 
     * {@link #NO_PASSWORD}, {@link #PASSWORD} and
     * {@link #USERNAME_AND_PASSWORD}
     */
    public void setAuthenticationMethod(Object authenticationMethod) {
        if (!(authenticationMethod instanceof String)) {
            this.authenticationMethod = NO_PASSWORD;
        } else if (authenticationMethod.equals(PASSWORD)
                || authenticationMethod.equals(USERNAME_AND_PASSWORD)) {
            this.authenticationMethod = authenticationMethod;
        } else {
            this.authenticationMethod = NO_PASSWORD;
        }
    }

    /**
     * Returns the authentication scheme. Default is {@link #DIGEST_SCHEME}
     */
    public Object getAuthenticationScheme() {
        return authenticationScheme;
    }

    /**
     * Sets the authentication scheme. Valid arguments are:
     * 
     * {@link #BASIC_SCHEME} and {@link #DIGEST_SCHEME}
     */
    public void setAuthenticationScheme(Object authenticationScheme) {
        if (!(authenticationScheme instanceof String)) {
            this.authenticationScheme = BASIC_SCHEME;
        } else if (authenticationScheme.equals(BASIC_SCHEME)
                || authenticationScheme.equals(DIGEST_SCHEME)) {
            this.authenticationScheme = authenticationScheme;
        } else {
            this.authenticationScheme = BASIC_SCHEME;
        }
    }

    public void setInetSocketAddress(int port) {
        setInetSocketAddress(new InetSocketAddress(port));
    }

    public void setInetSocketAddress(InetAddress address, int port) {
        setInetSocketAddress(new InetSocketAddress(address, port));
    }

    public void setInetSocketAddress(InetSocketAddress address) {
        this.address = address;
    }

    /**
     * An InetSocketAddress (IP:Port) to which the DAAP server will be bound
     * 
     * @return an InetSocketAddress
     */
    public InetSocketAddress getInetSocketAddress() {
        return address;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    /**
     * The ServerSocket backlog
     * 
     * @return the Backlog for the ServerSocket
     */
    public int getBacklog() {
        return backlog;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    /**
     * The maximum number of simultaneous incoming connections. Keep in mind
     * that there will be (in worst case) twice as many connections because each
     * DAAP connection has a separate audio stream
     * 
     * @return the maximum number of connections
     */
    public int getMaxConnections() {
        return maxConnections;
    }

    /**
     * Gets the ideal buffer size that should be used for streaming DAAP
     * connections.
     * 
     * @return
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /** Sets the buffer size. */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
