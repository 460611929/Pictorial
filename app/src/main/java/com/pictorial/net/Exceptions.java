package com.pictorial.net;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.pictorial.R;

public class Exceptions
{

	public static void errorHandle(Context context, VolleyError error)
	{
		if (error instanceof NetworkError)
		{
			Toast.makeText(context, context.getString(R.string.NetworkError), Toast.LENGTH_LONG).show();
		}
		else if (error instanceof NoConnectionError)
		{
			Toast.makeText(context, context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();
		}
		else if (error instanceof TimeoutError)
		{
			Toast.makeText(context, context.getString(R.string.TimeoutError), Toast.LENGTH_LONG).show();
		}

	}
}
