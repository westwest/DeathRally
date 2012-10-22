/*
 * Copyright (c) 2012 Mike Phoohad <anno.tao@gmail.com>
 * 
 * This file is part of DeathRally
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even implied warranty of
 * MERCHANTABILITY or FITTNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package dat255.HT2012.deathrally.game.model;

public class Player {
	private String name;
	private VehicleName vName;
	private VehicleType vType;
	private int vehicleID;
	
	public Player() {
		this("Player 1", VehicleName.TEST2_VEHICLE, VehicleType.CAR, 1);
	}
	
	public Player(String name, VehicleName v, VehicleType type, int id) {
		this.name = name;
		this.vName = v;
		this.vType = type;
		this.vehicleID = id;
	}
	
	public String getName() {
		return name;
	}
	public VehicleName getvName() {
		return vName;
	}
	public VehicleType getvType() {
		return vType;
	}
	public int getId() {
		return vehicleID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setvName(VehicleName vName) {
		this.vName = vName;
	}
	public void setvType(VehicleType vType) {
		this.vType = vType;
	}
	public void setId(int id) {
		this.vehicleID = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + vehicleID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((vName == null) ? 0 : vName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Player other = (Player) obj;
		if (vehicleID != other.vehicleID) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (vName != other.vName) {
			return false;
		}
		return true;
	}

}
