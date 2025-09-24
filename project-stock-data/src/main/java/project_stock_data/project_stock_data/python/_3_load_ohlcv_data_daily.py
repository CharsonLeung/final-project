import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy import MetaData
import psycopg2
import requests
from datetime import date, datetime, timedelta
from io import StringIO
import time
import sys
import subprocess

# 0 = Mon, 1 = Tue, 2 = Wed, 3 = Thu, 4 = Fri, 5 = Sat, 6 = Sun
if date.today().weekday() >=1 and date.today().weekday() < 6: # Monday -> Last Friday
  desired_date = date.today() - timedelta(days=1)
  desired_date_time = datetime(desired_date.year, desired_date.month, desired_date.day, 21, 30, 0)
  
  conn = psycopg2.connect(
  host="localhost",
  database="bc2504p",
  user="postgres",
  password="Admin1234$",
  port="5432"
  )

  stock_engine = create_engine("postgresql+psycopg2://postgres:Admin1234$@localhost:5432/bc2504p")
  df_sp500_symbols = pd.read_sql("select symbol from stock", con=stock_engine)


  ohlcv_engine = create_engine("postgresql+psycopg2://postgres:Admin1234$@localhost:5432/bc2504p")
  metadata = MetaData()



  period2 = round(desired_date_time.timestamp())
  sql_timestamp = pd.read_sql("select max(timestamp) from stock_ohlc_data", con=stock_engine)
  period1 = pd.DataFrame(sql_timestamp).iloc[0].iloc[0]
  df_ohlcv = []
  e = ""
  error_response = f"An unexpected error occurred: {e} on {datetime.now()}."

  for i in range (0, len(df_sp500_symbols)):
    start_time = time.perf_counter()
    try:
      url = str("https://query1.finance.yahoo.com/v8/finance/chart/" + df_sp500_symbols.symbol[i].replace('.','-') + "?period1=" + str(period1) + "&period2=" + str(period2) + "&interval=1d&events=history")
        
        # https://query1.finance.yahoo.com/v8/finance/chart/AAPL?period1=1657237004&period2=1752778462&interval=1d&events=history 
      headers = {
      'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36'
      }
      response = requests.get(url, headers=headers)
      print(url)
      df_ohlcv2 = pd.read_json(StringIO(response.text))
      df_ohlcv3 = (df_ohlcv2)['chart']['result'][0]['indicators']['quote'][0]
      df_symbol = (df_ohlcv2)['chart']['result'][0]['meta']['symbol']
      timestamp_loc = pd.DataFrame((df_ohlcv2)['chart']['result'][0]['timestamp'])
      date_time = []
    # print(timestamp_loc)
    # print(timestamp_loc.iloc[-1][0])
    # print(round(desired_date_time.timestamp()))
      if (timestamp_loc.iloc[-1][0] <= round(desired_date_time.timestamp())).any(0):
    #   print(len(timestamp_loc))
        for j in range (0, (len(timestamp_loc))):
    #     print(timestamp_loc.iloc[j][0])
    #     print(datetime.fromtimestamp(timestamp_loc.iloc[j][0]).strftime("%Y-%M-%D %H:%M:%S"))
          date_time.append(datetime.fromtimestamp(timestamp_loc.iloc[j][0]).strftime("%Y-%m-%d %H:%M:%S"))
            #print(date_time)
        low = ((df_ohlcv3)['low'][1:])#[1:len(timestamp_loc)-1])
        print(f"low = {low}")
        close = ((df_ohlcv3)['close'][1:])#[1:len(timestamp_loc)-1])
        print(f"close = {close}")
        high = ((df_ohlcv3)['high'][1:])#[1:len(timestamp_loc)-1])
        print(f"high = {high}")
        open = ((df_ohlcv3)['open'][1:])#[1:len(timestamp_loc)-1])
        print(f"open = {open}")
        volume = ((df_ohlcv3)['volume'][1:])#[1:len(timestamp_loc)-1])
        print(f"volume = {volume}")
        print(f"timestamp = {timestamp_loc[0][1:].to_list()}")
        timestamp_s = (timestamp_loc[0][1:].to_list())#[1:len(timestamp_loc)-1])
        print(f"date_time = {date_time[1:]}")
        date_time_s = (date_time[1:])#[1:len(timestamp_loc)-1])
        df_ohlcv4 = pd.DataFrame({
          'symbol' : df_symbol,
          'low_price_of_the_day' : low,
          'previous_close_price' : close,
          'high_price_of_the_day' : high,
          'open_price_of_the_day' : open,
          'volume' : volume,
          'timestamp' : timestamp_s,
          'date_time' : date_time_s
          })
        # print(df_ohlcv4)
        df_ohlcv.append(df_ohlcv4)
        df_pd_ohlcv = pd.concat(df_ohlcv, ignore_index=True)
      # print(df_pd_ohlcv)
        
      else:
        sys.exit("Updates are not available until the end of today trading hours.")
      
      end_time = time.perf_counter()
      elapsed_time = end_time - start_time
      request_time = i + 1
      #print(request_time)
      #print(df_symbol)
      #print(elapsed_time)
      print(f"request_time={request_time}, request_stock={df_symbol}, elapsed_time={elapsed_time}")
    except IndexError as e:
      print(error_response)
      try:
        with open("log.txt", "a") as file:
          file.write(f"{error_response}\n")
      except Exception as e:
        print(error_response)
      sys.exit("****Program terminated.****")
    except Exception as e:
      try:
        with open("log.txt", "a") as file:
          file.write(f"{error_response}\n")
      except Exception as e:
        print(error_response)
        
  df_pd_ohlcv.to_sql('stock_ohlc_data', ohlcv_engine, index=False, if_exists="append")
  # subprocess.run(["python", ])
  sys.exit("Update completed.")
  
    #except Exception as e:
    # print(f"Error {e} occurred.")
else:
  sys.exit("There should be no updates today.")

