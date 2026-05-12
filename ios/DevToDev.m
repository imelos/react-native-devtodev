#import "DevToDev.h"

#import <DTDAnalytics/DTDAnalytics-Swift.h>
#import <float.h>
#import <math.h>
#import <string.h>

@implementation DevToDev

RCT_EXPORT_MODULE();

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

RCT_EXPORT_METHOD(init:(NSString *)appId secretKey:(NSString *)secretKey)
{
  DTDAnalyticsConfiguration *configuration = [[DTDAnalyticsConfiguration alloc] init];
  [DTDAnalytics applicationKey:appId configuration:configuration];
  (void)secretKey;
}

RCT_EXPORT_METHOD(setCurrentLevel:(nonnull NSNumber *)currentLevel)
{
  [DTDAnalytics currentLevel:[currentLevel integerValue]];
}

RCT_EXPORT_METHOD(setUserId:(NSString *)activeUserId)
{
  [DTDAnalytics userId:activeUserId];
}

RCT_EXPORT_METHOD(levelUp:(nonnull NSNumber *)level)
{
  [DTDAnalytics levelUp:[level integerValue]];
}

RCT_EXPORT_METHOD(customEvent:(NSString *)eventName eventParams:(NSDictionary *)eventParams)
{
  if (eventName == nil || eventName.length == 0) {
    return;
  }

  if (eventParams == nil || eventParams.count == 0) {
    [DTDAnalytics customEvent:eventName];
    return;
  }

  DTDCustomEventParameters *parameters = [[DTDCustomEventParameters alloc] init];
  [eventParams enumerateKeysAndObjectsUsingBlock:^(id key, id value, BOOL *stop) {
    if (![key isKindOfClass:[NSString class]]) {
      return;
    }

    NSString *parameterKey = (NSString *)key;

    if ([value isKindOfClass:[NSString class]]) {
      [parameters addString:parameterKey value:(NSString *)value];
      return;
    }

    if ([value isKindOfClass:[NSNumber class]]) {
      NSNumber *numberValue = (NSNumber *)value;
      const char *numberType = [numberValue objCType];

      if (strcmp(numberType, @encode(BOOL)) == 0) {
        [parameters addBool:parameterKey value:[numberValue boolValue]];
        return;
      }

      double doubleValue = [numberValue doubleValue];
      double roundedValue = round(doubleValue);
      if (fabs(doubleValue - roundedValue) < DBL_EPSILON) {
        [parameters addInt:parameterKey value:[numberValue longLongValue]];
      } else {
        [parameters addDouble:parameterKey value:doubleValue];
      }
    }
  }];

  [DTDAnalytics customEvent:eventName withParameters:parameters];
}

@end
