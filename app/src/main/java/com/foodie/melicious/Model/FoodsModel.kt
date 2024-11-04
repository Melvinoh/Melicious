package com.foodie.melicious.Model

import android.os.Parcel
import android.os.Parcelable

data class FoodsModel(
    val bestFood: Boolean = false,
    val categoryId: Int = 0,
    val description: String = "",
    val Id: Int = 0,
    val picUrl: String = "",
    val price: Double = 0.0,
    val ingridience: List<String> = emptyList(),
    val size: List<Size> = emptyList(),
    val title: String = "",
    var numberInCart: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        bestFood = parcel.readByte() != 0.toByte(),
        categoryId = parcel.readInt(),
        description = parcel.readString().toString(),
        Id = parcel.readInt(),
        picUrl = parcel.readString().toString(),
        price = parcel.readDouble(),
        ingridience = parcel.createStringArrayList() ?: emptyList(),
        size = parcel.createTypedArrayList(Size.CREATOR) ?: emptyList(),
        title = parcel.readString().toString(),
        numberInCart = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.run {
            writeByte(if (bestFood) 1 else 0)
            writeInt(categoryId)
            writeString(description)
            writeInt(Id)
            writeString(picUrl)
            writeDouble(price)
            writeStringList(ingridience)
            writeTypedList(size)
            writeString(title)
            writeInt(numberInCart)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FoodsModel> {
        override fun createFromParcel(parcel: Parcel): FoodsModel {
            return FoodsModel(parcel)
        }

        override fun newArray(size: Int): Array<FoodsModel?> {
            return arrayOfNulls(size)
        }
    }

    data class Size(
        val weight: String = "",
        val price: Double = 0.0
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            weight = parcel.readString().toString(),
            price = parcel.readDouble()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(weight)
            parcel.writeDouble(price)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Size> {
            override fun createFromParcel(parcel: Parcel): Size {
                return Size(parcel)
            }

            override fun newArray(size: Int): Array<Size?> {
                return arrayOfNulls(size)
            }
        }
    }
}