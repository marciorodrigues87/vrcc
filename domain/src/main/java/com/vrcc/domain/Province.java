package com.vrcc.domain;

public class Province {

	private final String name;
	private final Boundaries boundaries;

	Province() {
		this(null, null);
	}

	private Province(String name, Boundaries boundaries) {
		this.name = name;
		this.boundaries = boundaries;
	}

	public static Province full(String name, Boundaries boundaries) {
		return new Province(name, boundaries);
	}

	public String getName() {
		return name;
	}

	public Boundaries getBoundaries() {
		return boundaries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boundaries == null) ? 0 : boundaries.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Province other = (Province) obj;
		if (boundaries == null) {
			if (other.boundaries != null)
				return false;
		} else if (!boundaries.equals(other.boundaries))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
