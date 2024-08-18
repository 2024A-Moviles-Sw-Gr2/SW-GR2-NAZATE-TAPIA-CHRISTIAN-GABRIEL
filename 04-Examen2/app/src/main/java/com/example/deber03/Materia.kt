package com.example.deber03

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

class Materia(
    var nombre: String?,
    //
    var fechaInicio: Date,
    var creditos: Int,
    var iraGeneral: Double,
    var estaActiva: Boolean
) : Parcelable {
    override fun toString(): String {
        return "$nombre - $iraGeneral"
    }
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        Date(parcel.readLong()),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    ) {

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeLong(fechaInicio.time)
        parcel.writeInt(creditos)
        parcel.writeDouble(iraGeneral)
        parcel.writeByte(if (estaActiva) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Materia> {
        override fun createFromParcel(parcel: Parcel): Materia {
            return Materia(parcel)
        }

        override fun newArray(size: Int): Array<Materia?> {
            return arrayOfNulls(size)
        }
    }
}