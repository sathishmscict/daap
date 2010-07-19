/*
 * Digital Audio Access Protocol (DAAP)
 * Copyright (C) 2004-2010 Roger Kapsi
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.ardverk.daap.bio;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A {@link ThreadFactory} for DAAP {@link Thread}s.
 */
class DaapThreadFactory implements ThreadFactory {
    
    private final AtomicInteger count = new AtomicInteger();
    
    private final String name;

    private final boolean daemon;
    
    public DaapThreadFactory(String name) {
        this(name, true);
    }
    
    DaapThreadFactory(String name, boolean daemon) {
        this.name = name;
        this.daemon = daemon;
    }

    private String createName() {
        return name + "-" + count.incrementAndGet();
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, createName());
        thread.setDaemon(daemon);
        return thread;
    }
}