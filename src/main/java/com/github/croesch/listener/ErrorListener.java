/*
 * Copyright (C) 2011  Christian Roesch
 * 
 * This file is part of crhcomponents.
 * 
 * crhcomponents is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * crhcomponents is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with crhcomponents.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.croesch.listener;

/**
 * Listener, that listens to error changes
 * 
 * @author croesch
 * @since Date: 2011/01/13
 */
public abstract class ErrorListener {

  /**
   * Will be invoked when the state of error changes
   * 
   * @param error {@code true}, if there is an error now
   * @since Date: 13.01.2011
   */
  public abstract void errorStateChanged(boolean error);
}
