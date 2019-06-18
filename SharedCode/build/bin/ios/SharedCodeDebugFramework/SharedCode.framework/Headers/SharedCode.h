#import <Foundation/Foundation.h>

@class SharedCodeSpecializationSourceImpl, SharedCodeSpecializationApi, SharedCodeSpecializationsRepository, SharedCodeGetSpecializations, SharedCodeMainPresenter, SharedCodeKotlinUnit, SharedCodeSpecialization, SharedCodeSpecializationApiCompanion, SharedCodeApiResult, SharedCodeData, SharedCodeApiResultCompanion, SharedCodeApiResult$serializer, SharedCodeKotlinArray, SharedCodeDataCompanion, SharedCodeData$serializer, SharedCodeIllness, SharedCodeSymptom, SharedCodeIllnessCompanion, SharedCodeIllness$serializer, SharedCodeOriginal, SharedCodeSpecializationCompanion, SharedCodeSpecialization$serializer, SharedCodeOriginalCompanion, SharedCodeOriginal$serializer, SharedCodeSymptomCompanion, SharedCodeSymptom$serializer, SharedCodeKotlinx_serialization_runtime_nativeEnumDescriptor, SharedCodeKotlinx_serialization_runtime_nativeSerialKind, SharedCodeKotlinNothing, SharedCodeKotlinx_serialization_runtime_nativeUpdateMode, SharedCodeKotlinx_serialization_runtime_nativeSerialClassDescImpl, SharedCodeKotlinEnum;

@protocol SharedCodeSpecializationSource, SharedCodeBasePresenter, SharedCodeBaseView, SharedCodeMainContractPresenter, SharedCodeMainContractView, SharedCodeMainContract, SharedCodeKotlinx_serialization_runtime_nativeKSerializer, SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer, SharedCodeKotlinx_serialization_runtime_nativeSerializationStrategy, SharedCodeKotlinx_serialization_runtime_nativeEncoder, SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor, SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy, SharedCodeKotlinx_serialization_runtime_nativeDecoder, SharedCodeKotlinIterator, SharedCodeKotlinx_serialization_runtime_nativeCompositeEncoder, SharedCodeKotlinx_serialization_runtime_nativeSerialContext, SharedCodeKotlinAnnotation, SharedCodeKotlinx_serialization_runtime_nativeCompositeDecoder, SharedCodeKotlinKClass, SharedCodeKotlinComparable, SharedCodeKotlinKDeclarationContainer, SharedCodeKotlinKAnnotatedElement, SharedCodeKotlinKClassifier;

NS_ASSUME_NONNULL_BEGIN

@interface KotlinBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface KotlinBase (KotlinBaseCopying) <NSCopying>
@end;

__attribute__((objc_runtime_name("KotlinMutableSet")))
__attribute__((swift_name("KotlinMutableSet")))
@interface SharedCodeMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((objc_runtime_name("KotlinMutableDictionary")))
__attribute__((swift_name("KotlinMutableDictionary")))
@interface SharedCodeMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((objc_runtime_name("KotlinNumber")))
__attribute__((swift_name("KotlinNumber")))
@interface SharedCodeNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((objc_runtime_name("KotlinByte")))
__attribute__((swift_name("KotlinByte")))
@interface SharedCodeByte : SharedCodeNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((objc_runtime_name("KotlinUByte")))
__attribute__((swift_name("KotlinUByte")))
@interface SharedCodeUByte : SharedCodeNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((objc_runtime_name("KotlinShort")))
__attribute__((swift_name("KotlinShort")))
@interface SharedCodeShort : SharedCodeNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((objc_runtime_name("KotlinUShort")))
__attribute__((swift_name("KotlinUShort")))
@interface SharedCodeUShort : SharedCodeNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((objc_runtime_name("KotlinInt")))
__attribute__((swift_name("KotlinInt")))
@interface SharedCodeInt : SharedCodeNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((objc_runtime_name("KotlinUInt")))
__attribute__((swift_name("KotlinUInt")))
@interface SharedCodeUInt : SharedCodeNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((objc_runtime_name("KotlinLong")))
__attribute__((swift_name("KotlinLong")))
@interface SharedCodeLong : SharedCodeNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((objc_runtime_name("KotlinULong")))
__attribute__((swift_name("KotlinULong")))
@interface SharedCodeULong : SharedCodeNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((objc_runtime_name("KotlinFloat")))
__attribute__((swift_name("KotlinFloat")))
@interface SharedCodeFloat : SharedCodeNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((objc_runtime_name("KotlinDouble")))
__attribute__((swift_name("KotlinDouble")))
@interface SharedCodeDouble : SharedCodeNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((objc_runtime_name("KotlinBoolean")))
__attribute__((swift_name("KotlinBoolean")))
@interface SharedCodeBoolean : SharedCodeNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((swift_name("SpecializationSource")))
@protocol SharedCodeSpecializationSource
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SpecializationSourceImpl")))
@interface SharedCodeSpecializationSourceImpl : KotlinBase <SharedCodeSpecializationSource>
- (instancetype)initWithSpecializationApi:(SharedCodeSpecializationApi *)specializationApi __attribute__((swift_name("init(specializationApi:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SpecializationsRepository")))
@interface SharedCodeSpecializationsRepository : KotlinBase
- (instancetype)initWithSpecializationSource:(id<SharedCodeSpecializationSource>)specializationSource __attribute__((swift_name("init(specializationSource:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetSpecializations")))
@interface SharedCodeGetSpecializations : KotlinBase
- (instancetype)initWithSpecializationsRepository:(SharedCodeSpecializationsRepository *)specializationsRepository __attribute__((swift_name("init(specializationsRepository:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("BasePresenter")))
@protocol SharedCodeBasePresenter
@required
- (void)start __attribute__((swift_name("start()")));
@end;

__attribute__((swift_name("BaseView")))
@protocol SharedCodeBaseView
@required
@property id _Nullable presenter;
@end;

__attribute__((swift_name("MainContractPresenter")))
@protocol SharedCodeMainContractPresenter <SharedCodeBasePresenter>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MainPresenter")))
@interface SharedCodeMainPresenter : KotlinBase <SharedCodeMainContractPresenter>
- (instancetype)initWithView:(id<SharedCodeMainContractView>)view getSpecializations:(SharedCodeGetSpecializations *)getSpecializations __attribute__((swift_name("init(view:getSpecializations:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("MainContract")))
@protocol SharedCodeMainContract
@required
@end;

__attribute__((swift_name("MainContractView")))
@protocol SharedCodeMainContractView <SharedCodeBaseView>
@required
- (void)setProgressIndicatorActive:(BOOL)active __attribute__((swift_name("setProgressIndicator(active:)")));
- (void)showStatisticsNumberOfIncompleteTasks:(int32_t)numberOfIncompleteTasks numberOfCompletedTasks:(int32_t)numberOfCompletedTasks __attribute__((swift_name("showStatistics(numberOfIncompleteTasks:numberOfCompletedTasks:)")));
- (void)showLoadingStatisticsError __attribute__((swift_name("showLoadingStatisticsError()")));
@property (readonly) BOOL isActive;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SpecializationApi")))
@interface SharedCodeSpecializationApi : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)fetchSpecializationsCallback:(SharedCodeKotlinUnit *(^)(SharedCodeSpecialization *))callback __attribute__((swift_name("fetchSpecializations(callback:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SpecializationApi.Companion")))
@interface SharedCodeSpecializationApiCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApiResult")))
@interface SharedCodeApiResult : KotlinBase
- (instancetype)initWithData:(NSArray<SharedCodeData *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (NSArray<SharedCodeData *> *)component1 __attribute__((swift_name("component1()")));
- (SharedCodeApiResult *)doCopyData:(NSArray<SharedCodeData *> *)data __attribute__((swift_name("doCopy(data:)")));
@property (readonly) NSArray<SharedCodeData *> *data;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApiResult.Companion")))
@interface SharedCodeApiResultCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerializationStrategy")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeSerializationStrategy
@required
- (void)serializeEncoder:(id<SharedCodeKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(id _Nullable)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@property (readonly) id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor> descriptor;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeDeserializationStrategy")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (id _Nullable)patchDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder old:(id _Nullable)old __attribute__((swift_name("patch(decoder:old:)")));
@property (readonly) id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor> descriptor;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeKSerializer")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeKSerializer <SharedCodeKotlinx_serialization_runtime_nativeSerializationStrategy, SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>
@required
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeGeneratedSerializer")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer <SharedCodeKotlinx_serialization_runtime_nativeKSerializer>
@required
- (SharedCodeKotlinArray *)childSerializers __attribute__((swift_name("childSerializers()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApiResult.$serializer")))
@interface SharedCodeApiResult$serializer : KotlinBase <SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (SharedCodeApiResult *)deserializeDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (SharedCodeApiResult *)patchDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder old:(SharedCodeApiResult *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<SharedCodeKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(SharedCodeApiResult *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Data")))
@interface SharedCodeData : KotlinBase
- (instancetype)initWithImages:(SharedCodeSpecialization *)images __attribute__((swift_name("init(images:)"))) __attribute__((objc_designated_initializer));
- (SharedCodeSpecialization *)component1 __attribute__((swift_name("component1()")));
- (SharedCodeData *)doCopyImages:(SharedCodeSpecialization *)images __attribute__((swift_name("doCopy(images:)")));
@property (readonly) SharedCodeSpecialization *images;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Data.Companion")))
@interface SharedCodeDataCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Data.$serializer")))
@interface SharedCodeData$serializer : KotlinBase <SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (SharedCodeData *)deserializeDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (SharedCodeData *)patchDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder old:(SharedCodeData *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<SharedCodeKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(SharedCodeData *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Illness")))
@interface SharedCodeIllness : KotlinBase
- (instancetype)initWithName:(NSString *)name symptoms:(NSArray<SharedCodeSymptom *> *)symptoms __attribute__((swift_name("init(name:symptoms:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSArray<SharedCodeSymptom *> *)component2 __attribute__((swift_name("component2()")));
- (SharedCodeIllness *)doCopyName:(NSString *)name symptoms:(NSArray<SharedCodeSymptom *> *)symptoms __attribute__((swift_name("doCopy(name:symptoms:)")));
@property (readonly) NSString *name;
@property (readonly) NSArray<SharedCodeSymptom *> *symptoms;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Illness.Companion")))
@interface SharedCodeIllnessCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Illness.$serializer")))
@interface SharedCodeIllness$serializer : KotlinBase <SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (SharedCodeIllness *)deserializeDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (SharedCodeIllness *)patchDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder old:(SharedCodeIllness *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<SharedCodeKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(SharedCodeIllness *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Specialization")))
@interface SharedCodeSpecialization : KotlinBase
- (instancetype)initWithName:(NSString *)name illnesses:(NSArray<SharedCodeIllness *> *)illnesses original:(SharedCodeOriginal * _Nullable)original __attribute__((swift_name("init(name:illnesses:original:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSArray<SharedCodeIllness *> *)component2 __attribute__((swift_name("component2()")));
- (SharedCodeOriginal * _Nullable)component3 __attribute__((swift_name("component3()")));
- (SharedCodeSpecialization *)doCopyName:(NSString *)name illnesses:(NSArray<SharedCodeIllness *> *)illnesses original:(SharedCodeOriginal * _Nullable)original __attribute__((swift_name("doCopy(name:illnesses:original:)")));
@property (readonly) NSString *name;
@property (readonly) NSArray<SharedCodeIllness *> *illnesses;
@property (readonly) SharedCodeOriginal * _Nullable original;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Specialization.Companion")))
@interface SharedCodeSpecializationCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Specialization.$serializer")))
@interface SharedCodeSpecialization$serializer : KotlinBase <SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (SharedCodeSpecialization *)deserializeDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (SharedCodeSpecialization *)patchDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder old:(SharedCodeSpecialization *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<SharedCodeKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(SharedCodeSpecialization *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Original")))
@interface SharedCodeOriginal : KotlinBase
- (instancetype)initWithUrl:(NSString *)url __attribute__((swift_name("init(url:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (SharedCodeOriginal *)doCopyUrl:(NSString *)url __attribute__((swift_name("doCopy(url:)")));
@property (readonly) NSString *url;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Original.Companion")))
@interface SharedCodeOriginalCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Original.$serializer")))
@interface SharedCodeOriginal$serializer : KotlinBase <SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (SharedCodeOriginal *)deserializeDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (SharedCodeOriginal *)patchDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder old:(SharedCodeOriginal *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<SharedCodeKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(SharedCodeOriginal *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Symptom")))
@interface SharedCodeSymptom : KotlinBase
- (instancetype)initWithName:(NSString *)name __attribute__((swift_name("init(name:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (SharedCodeSymptom *)doCopyName:(NSString *)name __attribute__((swift_name("doCopy(name:)")));
@property (readonly) NSString *name;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Symptom.Companion")))
@interface SharedCodeSymptomCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Symptom.$serializer")))
@interface SharedCodeSymptom$serializer : KotlinBase <SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (SharedCodeSymptom *)deserializeDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (SharedCodeSymptom *)patchDecoder:(id<SharedCodeKotlinx_serialization_runtime_nativeDecoder>)decoder old:(SharedCodeSymptom *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<SharedCodeKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(SharedCodeSymptom *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ActualKt")))
@interface SharedCodeActualKt : KotlinBase
+ (NSString *)platformName __attribute__((swift_name("platformName()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommonKt")))
@interface SharedCodeCommonKt : KotlinBase
+ (NSString *)createApplicationScreenMessage __attribute__((swift_name("createApplicationScreenMessage()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface SharedCodeKotlinUnit : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface SharedCodeKotlinArray : KotlinBase
+ (instancetype)arrayWithSize:(int32_t)size init:(id _Nullable (^)(SharedCodeInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (id _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<SharedCodeKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(id _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeEncoder")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeEncoder
@required
- (id<SharedCodeKotlinx_serialization_runtime_nativeCompositeEncoder>)beginCollectionDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc collectionSize:(int32_t)collectionSize typeParams:(SharedCodeKotlinArray *)typeParams __attribute__((swift_name("beginCollection(desc:collectionSize:typeParams:)")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeCompositeEncoder>)beginStructureDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc typeParams:(SharedCodeKotlinArray *)typeParams __attribute__((swift_name("beginStructure(desc:typeParams:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescription:(SharedCodeKotlinx_serialization_runtime_nativeEnumDescriptor *)enumDescription ordinal:(int32_t)ordinal __attribute__((swift_name("encodeEnum(enumDescription:ordinal:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));
- (void)encodeNull __attribute__((swift_name("encodeNull()")));
- (void)encodeNullableSerializableValueSerializer:(id<SharedCodeKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<SharedCodeKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
- (void)encodeUnit __attribute__((swift_name("encodeUnit()")));
@property (readonly) id<SharedCodeKotlinx_serialization_runtime_nativeSerialContext> context;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialDescriptor")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor
@required
- (NSArray<id<SharedCodeKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (NSArray<id<SharedCodeKotlinAnnotation>> *)getEntityAnnotations __attribute__((swift_name("getEntityAnnotations()")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) int32_t elementsCount;
@property (readonly) BOOL isNullable;
@property (readonly) SharedCodeKotlinx_serialization_runtime_nativeSerialKind *kind;
@property (readonly) NSString *name;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeDecoder")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeDecoder
@required
- (id<SharedCodeKotlinx_serialization_runtime_nativeCompositeDecoder>)beginStructureDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc typeParams:(SharedCodeKotlinArray *)typeParams __attribute__((swift_name("beginStructure(desc:typeParams:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescription:(SharedCodeKotlinx_serialization_runtime_nativeEnumDescriptor *)enumDescription __attribute__((swift_name("decodeEnum(enumDescription:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));
- (SharedCodeKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
- (void)decodeUnit __attribute__((swift_name("decodeUnit()")));
- (id _Nullable)updateNullableSerializableValueDeserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateNullableSerializableValue(deserializer:old:)")));
- (id _Nullable)updateSerializableValueDeserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateSerializableValue(deserializer:old:)")));
@property (readonly) id<SharedCodeKotlinx_serialization_runtime_nativeSerialContext> context;
@property (readonly) SharedCodeKotlinx_serialization_runtime_nativeUpdateMode *updateMode;
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol SharedCodeKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeCompositeEncoder")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeCompositeEncoder
@required
- (void)encodeBooleanElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(desc:index:value:)")));
- (void)encodeByteElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(desc:index:value:)")));
- (void)encodeCharElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(desc:index:value:)")));
- (void)encodeDoubleElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(desc:index:value:)")));
- (void)encodeFloatElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(desc:index:value:)")));
- (void)encodeIntElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(desc:index:value:)")));
- (void)encodeLongElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(desc:index:value:)")));
- (void)encodeNonSerializableElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(id)value __attribute__((swift_name("encodeNonSerializableElement(desc:index:value:)")));
- (void)encodeNullableSerializableElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index serializer:(id<SharedCodeKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(desc:index:serializer:value:)")));
- (void)encodeSerializableElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index serializer:(id<SharedCodeKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(desc:index:serializer:value:)")));
- (void)encodeShortElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(desc:index:value:)")));
- (void)encodeStringElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(desc:index:value:)")));
- (void)encodeUnitElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("encodeUnitElement(desc:index:)")));
- (void)endStructureDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("endStructure(desc:)")));
- (BOOL)shouldEncodeElementDefaultDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(desc:index:)")));
@property (readonly) id<SharedCodeKotlinx_serialization_runtime_nativeSerialContext> context;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialClassDescImpl")))
@interface SharedCodeKotlinx_serialization_runtime_nativeSerialClassDescImpl : KotlinBase <SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>
- (instancetype)initWithName:(NSString *)name generatedSerializer:(id<SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer> _Nullable)generatedSerializer __attribute__((swift_name("init(name:generatedSerializer:)"))) __attribute__((objc_designated_initializer));
- (void)addElementName:(NSString *)name isOptional:(BOOL)isOptional __attribute__((swift_name("addElement(name:isOptional:)")));
- (void)pushAnnotationA:(id<SharedCodeKotlinAnnotation>)a __attribute__((swift_name("pushAnnotation(a:)")));
- (void)pushClassAnnotationA:(id<SharedCodeKotlinAnnotation>)a __attribute__((swift_name("pushClassAnnotation(a:)")));
- (void)pushDescriptorDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("pushDescriptor(desc:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeEnumDescriptor")))
@interface SharedCodeKotlinx_serialization_runtime_nativeEnumDescriptor : SharedCodeKotlinx_serialization_runtime_nativeSerialClassDescImpl
- (instancetype)initWithName:(NSString *)name choices:(SharedCodeKotlinArray *)choices __attribute__((swift_name("init(name:choices:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithName:(NSString *)name generatedSerializer:(id<SharedCodeKotlinx_serialization_runtime_nativeGeneratedSerializer> _Nullable)generatedSerializer __attribute__((swift_name("init(name:generatedSerializer:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialContext")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeSerialContext
@required
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer> _Nullable)getKclass:(id<SharedCodeKotlinKClass>)kclass __attribute__((swift_name("get(kclass:)")));
- (id<SharedCodeKotlinx_serialization_runtime_nativeKSerializer> _Nullable)getByValueValue:(id)value __attribute__((swift_name("getByValue(value:)")));
@end;

__attribute__((swift_name("KotlinAnnotation")))
@protocol SharedCodeKotlinAnnotation
@required
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialKind")))
@interface SharedCodeKotlinx_serialization_runtime_nativeSerialKind : KotlinBase
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeCompositeDecoder")))
@protocol SharedCodeKotlinx_serialization_runtime_nativeCompositeDecoder
@required
- (BOOL)decodeBooleanElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(desc:index:)")));
- (int8_t)decodeByteElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeByteElement(desc:index:)")));
- (unichar)decodeCharElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeCharElement(desc:index:)")));
- (int32_t)decodeCollectionSizeDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("decodeCollectionSize(desc:)")));
- (double)decodeDoubleElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(desc:index:)")));
- (int32_t)decodeElementIndexDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("decodeElementIndex(desc:)")));
- (float)decodeFloatElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeFloatElement(desc:index:)")));
- (int32_t)decodeIntElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeIntElement(desc:index:)")));
- (int64_t)decodeLongElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeLongElement(desc:index:)")));
- (id _Nullable)decodeNullableSerializableElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableElement(desc:index:deserializer:)")));
- (id _Nullable)decodeSerializableElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableElement(desc:index:deserializer:)")));
- (int16_t)decodeShortElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeShortElement(desc:index:)")));
- (NSString *)decodeStringElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeStringElement(desc:index:)")));
- (void)decodeUnitElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeUnitElement(desc:index:)")));
- (void)endStructureDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("endStructure(desc:)")));
- (id _Nullable)updateNullableSerializableElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateNullableSerializableElement(desc:index:deserializer:old:)")));
- (id _Nullable)updateSerializableElementDesc:(id<SharedCodeKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<SharedCodeKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateSerializableElement(desc:index:deserializer:old:)")));
@property (readonly) id<SharedCodeKotlinx_serialization_runtime_nativeSerialContext> context;
@property (readonly) SharedCodeKotlinx_serialization_runtime_nativeUpdateMode *updateMode;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface SharedCodeKotlinNothing : KotlinBase
@end;

__attribute__((swift_name("KotlinComparable")))
@protocol SharedCodeKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("KotlinEnum")))
@interface SharedCodeKotlinEnum : KotlinBase <SharedCodeKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
- (int32_t)compareToOther:(SharedCodeKotlinEnum *)other __attribute__((swift_name("compareTo(other:)")));
@property (readonly) NSString *name;
@property (readonly) int32_t ordinal;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_runtime_nativeUpdateMode")))
@interface SharedCodeKotlinx_serialization_runtime_nativeUpdateMode : SharedCodeKotlinEnum
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
@property (class, readonly) SharedCodeKotlinx_serialization_runtime_nativeUpdateMode *banned;
@property (class, readonly) SharedCodeKotlinx_serialization_runtime_nativeUpdateMode *overwrite;
@property (class, readonly) SharedCodeKotlinx_serialization_runtime_nativeUpdateMode *update;
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (int32_t)compareToOther:(SharedCodeKotlinx_serialization_runtime_nativeUpdateMode *)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol SharedCodeKotlinKDeclarationContainer
@required
@end;

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol SharedCodeKotlinKAnnotatedElement
@required
@end;

__attribute__((swift_name("KotlinKClassifier")))
@protocol SharedCodeKotlinKClassifier
@required
@end;

__attribute__((swift_name("KotlinKClass")))
@protocol SharedCodeKotlinKClass <SharedCodeKotlinKDeclarationContainer, SharedCodeKotlinKAnnotatedElement, SharedCodeKotlinKClassifier>
@required
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName;
@property (readonly) NSString * _Nullable simpleName;
@end;

NS_ASSUME_NONNULL_END
