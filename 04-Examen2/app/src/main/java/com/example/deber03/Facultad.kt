package com.example.deber03

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

class Facultad(
    //Nombre facultad
    var nombre: String?,
    //Fecha de incio de matriculas
    var fechaMatriculas: Date,
    var numeroProfesores: Int,
    var presupuesto: Double,
    //var ubicacion: String?,
    var enClases: Boolean
) : Parcelable {
    override fun toString(): String {
        return "$nombre - $presupuesto"
    }
    constructor(parcel: Parcel) : this(
        parcel.readString(),

        Date(parcel.readLong()),
        parcel.readInt(),
        parcel.readDouble(),
        //parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeLong(fechaMatriculas.time)
        parcel.writeInt(numeroProfesores)
        parcel.writeDouble(presupuesto)
        //parcel.writeString(ubicacion)
        parcel.writeByte(if (enClases) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Facultad> {
        override fun createFromParcel(parcel: Parcel): Facultad {
            return Facultad(parcel)
        }

        override fun newArray(size: Int): Array<Facultad?> {
            return arrayOfNulls(size)
        }
    }
}