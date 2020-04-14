/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.lunisolar.magma.func.supp;

import javax.annotation.Nonnull;

import static eu.lunisolar.magma.func.supp.MsgVerbosity.Constants.*;
import static java.lang.String.*;

/**
 *
 */
public enum MsgVerbosity {

	MIN(MINf + C + M, MINf + C + M, MINf + C + M, MINf + C + M, MINf + C + UM, MINf + C + UM, MINf + C + UM, MINf + C + UM, MINf + C + MUM, MINf + C + MUM, MINf + C + MUM, MINf + C + MUM),

	/** Only actual value (value under check) will be part of the message (others need to be explicitly included in custom part of the message) */
	VAL(VALf + C + M, VALf + C + M, VALf + C + M, VALf + C + M, VALf + C + UM, VALf + C + UM, VALf + C + UM, VALf + C + UM, VALf + C + MUM, VALf + C + MUM, VALf + C + MUM, VALf + C + MUM),

	/** Only params will be part of the message (actual value - value under check - need to be explicitly included in custom part of the message) */
	PARAMS(PAR_1f + C + M, PAR_2f + C + M, PAR_3f + C + M, PAR_4f + C + M, PAR_1f + C + UM, PAR_2f + C + UM, PAR_3f + C + UM, PAR_4f + C + UM, PAR_1f + C + MUM, PAR_2f + C + MUM, PAR_3f + C + MUM, PAR_4f + C + MUM),

	/** All values will be included in core part of the message (actual/value-under-check + all possible params). */
	ALL(ALL_1f + C + M, ALL_2f + C + M, ALL_3f + C + M, ALL_4f + C + M, ALL_1f + C + UM, ALL_2f + C + UM, ALL_3f + C + UM, ALL_4f + C + UM, ALL_1f + C + MUM, ALL_2f + C + MUM, ALL_3f + C + MUM, ALL_4f + C + MUM),

	//
	;

	private final @Nonnull String format1M;
	private final @Nonnull String format2M;
	private final @Nonnull String format3M;
	private final @Nonnull String format4M;

	private final @Nonnull String format1UM;
	private final @Nonnull String format2UM;
	private final @Nonnull String format3UM;
	private final @Nonnull String format4UM;

	private final @Nonnull String format1MUM;
	private final @Nonnull String format2MUM;
	private final @Nonnull String format3MUM;
	private final @Nonnull String format4MUM;

	MsgVerbosity(@Nonnull String format1M, @Nonnull String format2M, @Nonnull String format3M, @Nonnull String format4M, @Nonnull String format1UM, @Nonnull String format2UM, @Nonnull String format3UM, @Nonnull String format4UM,
			@Nonnull String format1MUM, @Nonnull String format2MUM, @Nonnull String format3MUM, @Nonnull String format4MUM) {
		this.format1M = format1M;
		this.format2M = format2M;
		this.format3M = format3M;
		this.format4M = format4M;
		this.format1UM = format1UM;
		this.format2UM = format2UM;
		this.format3UM = format3UM;
		this.format4UM = format4UM;
		this.format1MUM = format1MUM;
		this.format2MUM = format2MUM;
		this.format3MUM = format3MUM;
		this.format4MUM = format4MUM;
	}

	static class Constants {
		public static int MAX_DOMAIN = 4;

		public static int TYPEi = MAX_DOMAIN + 1;
		public static int NAMEi = MAX_DOMAIN + 2;
		public static int M1i = MAX_DOMAIN + 3;
		public static int M2i = MAX_DOMAIN + 4;

		public static final String MINf = format("%%%d$s [%%%d$s]", TYPEi, NAMEi);
		public static final String VALf = format("%%%d$s [%%%d$s=='%%s']", TYPEi, NAMEi);
		public static final String PAR_1f = format("%%%d$s [%%%d$s]", TYPEi, NAMEi);
		public static final String PAR_2f = format("%%%d$s [%%%d$s](param: '%%2$s')", TYPEi, NAMEi);
		public static final String PAR_3f = format("%%%d$s [%%%d$s](params: '%%2$s', '%%3$s')", TYPEi, NAMEi);
		public static final String PAR_4f = format("%%%d$s [%%%d$s](params: '%%2$s', '%%3$s', '%%4$s')", TYPEi, NAMEi);
		public static final String ALL_1f = format("%%%d$s [%%%d$s=='%%s']", TYPEi, NAMEi);
		public static final String ALL_2f = format("%%%d$s [%%%d$s=='%%s'](param: '%%s')", TYPEi, NAMEi);
		public static final String ALL_3f = format("%%%d$s [%%%d$s=='%%s'](params: '%%s', '%%s')", TYPEi, NAMEi);
		public static final String ALL_4f = format("%%%d$s [%%%d$s=='%%s'](params: '%%s', '%%s', '%%s')", TYPEi, NAMEi);

		public static final String C = ":";
		public static final String M = format(" %%%d$s", M1i);
		public static final String UM = format(" %%%d$s", M2i);
		public static final String MUM = format(" %%%d$s - %%%d$s", M1i, M2i);
	}

	@Nonnull
	public String format1M() {
		return format1M;
	}
	@Nonnull
	public String format2M() {
		return format2M;
	}
	@Nonnull
	public String format3M() {
		return format3M;
	}
	@Nonnull
	public String format4M() {
		return format4M;
	}
	@Nonnull
	public String format1UM() {
		return format1UM;
	}
	@Nonnull
	public String format2UM() {
		return format2UM;
	}
	@Nonnull
	public String format3UM() {
		return format3UM;
	}
	@Nonnull
	public String format4UM() {
		return format4UM;
	}
	@Nonnull
	public String format1MUM() {
		return format1MUM;
	}
	@Nonnull
	public String format2MUM() {
		return format2MUM;
	}
	@Nonnull
	public String format3MUM() {
		return format3MUM;
	}
	@Nonnull
	public String format4MUM() {
		return format4MUM;
	}

}